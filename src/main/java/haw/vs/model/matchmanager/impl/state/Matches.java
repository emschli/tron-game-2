package haw.vs.model.matchmanager.impl.state;

import haw.vs.common.Direction;
import haw.vs.common.PlayerConfigData;
import haw.vs.model.common.Match;
import haw.vs.model.common.MatchState;
import haw.vs.model.common.Player;
import haw.vs.model.common.PlayerState;
import haw.vs.model.matchmanager.MatchManagerInfo;
import haw.vs.model.matchmanager.impl.tick.TickCounter;
import haw.vs.model.matchmanager.impl.tick.TickSummary;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class Matches {
    private static long MATCH_ID_COUNTER = 1;
    private static Matches INSTANCE;
    public static int MAX_NUMBER_OF_PLAYERS = 4;

    private final Map<Integer, Queue<Match>> waitingQueues;
    private final Map<Long, Match> runningMatches;


    private final BlockingQueue<Match> matchesReadyForViewUpdate;
    private final BlockingQueue<MenuEvent> menuEvents;

    private final ReentrantLock runningMatchesMutex = new ReentrantLock();

    public ReentrantLock inputLock = new ReentrantLock();
    public ReentrantLock updateLock = new ReentrantLock();

    private Matches() {
        waitingQueues = new HashMap<>();
        for (int i = 1; i <=MAX_NUMBER_OF_PLAYERS ; i++) {
            waitingQueues.put(i, new LinkedList<>());
        }
        runningMatches = new HashMap<>();
        matchesReadyForViewUpdate = new LinkedBlockingDeque<>(200);
        menuEvents = new LinkedBlockingDeque<>(200);
    }

    public static synchronized Matches getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Matches();
        }

        return INSTANCE;
    }

    public void addMenuEvent(MenuEvent event) {
        menuEvents.add(event);
    }

    public MenuEvent getNextMenuEvent() throws InterruptedException {
        return menuEvents.take();
    }

    public Match addPlayerToMatch(long playerId, int numberOfPlayers, PlayerConfigData configData) {
        Player player = createNewPlayer(playerId, configData);

        Match match = getFirstNotFullMatch(numberOfPlayers);
        match.addPlayer(player);
        applyColors(match);

        return match;
    }

    /**
     * Removes Player from waiting Match
     * @return The Match the player was removed from or null if there was no match or the Player was the last one in the match
     */
    public Match removePlayerFromMatchWaiting(long playerId, long matchId, int numberOfPlayers) {
        Match match = waitingQueues.get(numberOfPlayers)
                .stream()
                .filter(m -> m.getMatchId() == matchId)
                .findFirst()
                .orElse(null);

        if (match != null) {
            match.removePlayer(playerId);
            applyColors(match);
            if (match.getPlayers().isEmpty()) {
                waitingQueues.get(numberOfPlayers).remove(match);
                match = null;
            }
        }

        return match;
    }

    public void moveMatchToRunningMatches(Match match) {
        waitingQueues.get(match.getNumberOfPlayers()).remove(match);
        match.setState(MatchState.READY);

        inputLock.lock();
        putRunningMatch(match);
        inputLock.unlock();
    }

    public List<Match> getRunningMatches() {
        List<Match> matches;
        runningMatchesMutex.lock();
        matches = runningMatches.values().stream().collect(Collectors.toList());
        runningMatchesMutex.unlock();
        return matches;
    }

    public Match getNextMatchForViewUpdate() throws InterruptedException {
        return matchesReadyForViewUpdate.take();
    }

    public void updateMatch(Match match) {
        updateLock.lock();
        Match matchToUpdate = getRunningMatch(match.getMatchId());

        if(matchToUpdate==null){
            updateLock.unlock();
            return;
        }
        // Match belongs to old tick!
        if (match.getTickStamp() < TickCounter.getTickCounter()) {
            updateLock.unlock();
            return;
        }

        matchToUpdate.setState(match.getState());
        matchToUpdate.setMaxGridX(match.getMaxGridX());
        matchToUpdate.setMaxGridY(match.getMaxGridY());
        matchToUpdate.setPlayers(copyPlayers(match));
        matchToUpdate.setTickStamp(match.getTickStamp());

        switch (match.getState()) {
            case READY:
                startGame(matchToUpdate);
                matchToUpdate.setState(MatchState.RUNNING);
                break;
            case RUNNING:
                updateRunningMatch(matchToUpdate);
                break;
            case ENDED:
                updateEndedMatch(matchToUpdate);
                break;
        }

        matchesReadyForViewUpdate.add(match); //just add the copy
        TickSummary.addMatchesReceivedFromGameLogic();
        updateLock.unlock();
    }

    private void removeEndedMatch(Match match) {
        runningMatchesMutex.lock();
        runningMatches.remove(match.getMatchId());
        runningMatchesMutex.unlock();
    }

    public void makePlayerMove(long playerId, long matchId, Direction direction) {
        inputLock.lock();
        Match match = getRunningMatch(matchId);
        if(match == null){
            inputLock.unlock();
            return;
        }
        Player player = match.getPlayerById(playerId);
        if(player == null){
            inputLock.unlock();
            return;
        }
        player.setNextDirection(direction);
        inputLock.unlock();
    }

    private Match getRunningMatch(long matchId) {
        runningMatchesMutex.lock();
        Match match = runningMatches.get(matchId);
        runningMatchesMutex.unlock();
        return match;
    }

    private void putRunningMatch(Match match) {
        runningMatchesMutex.lock();
        runningMatches.put(match.getMatchId(), match);
        runningMatchesMutex.unlock();
    }

    private Player createNewPlayer(long playerId, PlayerConfigData configData) {
        Player player = new Player();
        player.setPlayerId(playerId);
        player.setConfigData(configData);
        player.setState(PlayerState.WAITING);
        return player;
    }

    private Match createNewMatch(int numberOfPlayers) {
        Match match = new Match();
        match.setMatchId(MATCH_ID_COUNTER);
        MATCH_ID_COUNTER += 1;
        match.setNumberOfPlayers(numberOfPlayers);
        match.setState(MatchState.WAITING);
        match.setMatchManagerId(MatchManagerInfo.getMatchManagerId());
        return match;
    }

    private void applyColors(Match match) {
        for (int i = 0; i < match.getPlayers().size(); i++) {
            Player player = match.getPlayers().get(i);
            player.setColor(Colors.getColor(i + 1));
        }
    }

    //Get first not full match or create new one
    private Match getFirstNotFullMatch(int numberOfPlayers) {
        Optional<Match> matchOpt = waitingQueues.get(numberOfPlayers)
                .stream()
                .filter(match -> !match.isFull())
                .findFirst();
        if (matchOpt.isPresent()) {
            return matchOpt.get();
        } else {
            Match newMatch = createNewMatch(numberOfPlayers);
            waitingQueues.get(numberOfPlayers).add(newMatch);
            return newMatch;
        }
    }

    private void startGame(Match match) {
        for (Player player : match.getPlayers()) {
            player.setState(PlayerState.PLAYING);
        }
    }

    private void updateRunningMatch(Match match) {
        for (Player player: new ArrayList<>(match.getPlayers())) {
            if (player.getState() == PlayerState.DEAD) {
                match.removePlayer(player);
            }
        }
    }

    private void updateEndedMatch(Match match) {
        removeEndedMatch(match);
    }

    private List<Player> copyPlayers(Match match) {
        List<Player> players = new ArrayList<>();

        for (Player player : match.getPlayers()) {
            players.add(player.copy());
        }

        return players;
    }
}
