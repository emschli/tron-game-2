package haw.vs.model.matchmanager.state;

import haw.vs.common.Direction;
import haw.vs.common.PlayerConfigData;
import haw.vs.model.common.Match;
import haw.vs.model.common.MatchState;
import haw.vs.model.common.Player;
import haw.vs.model.common.PlayerState;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

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
    public ReentrantLock viewUpdateLock = new ReentrantLock();
    public Condition startWork = viewUpdateLock.newCondition();
    public Condition cleanupDone = viewUpdateLock.newCondition();


    private Matches() {
        waitingQueues = new HashMap<>();
        for (int i = 1; i <=MAX_NUMBER_OF_PLAYERS ; i++) {
            waitingQueues.put(i, new LinkedList<>());
        }
        runningMatches = new HashMap<>();
        matchesReadyForViewUpdate = new LinkedBlockingDeque<>(200); //TODO: capacity ok?
        menuEvents = new LinkedBlockingDeque<>(200); //TODO: capacity ok?
    }

    public static Matches getInstance() {
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
        int playerNumber = match.getPlayers().indexOf(player) + 1;
        player.setColor(Colors.getColor(playerNumber));

        return match;
    }

    public Match removePlayerFromMatch(long playerId, long matchId, int numberOfPlayers) {
        Match match = waitingQueues.get(numberOfPlayers)
                .stream()
                .filter(m -> m.getMatchId() == matchId)
                .findFirst()
                .orElse(null);

        if (match != null) {
            match.removePlayer(playerId);
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
        matches = runningMatches.values().stream().toList();
        runningMatchesMutex.unlock();
        return matches;
    }

    public Match getNextMatchForViewUpdate() throws InterruptedException {
        return matchesReadyForViewUpdate.take();
    }

    public boolean hasNextMatchForViewUpdate() {
        return !matchesReadyForViewUpdate.isEmpty();
    }

    public void updateMatch(Match match) {
        updateLock.lock();
        Match matchToUpdate = getRunningMatch(match.getMatchId());
        matchToUpdate.setState(match.getState());
        matchToUpdate.setMaxGridX(match.getMaxGridX());
        matchToUpdate.setMaxGridY(match.getMaxGridY());
        matchToUpdate.setPlayers(match.getPlayers());
        matchesReadyForViewUpdate.add(matchToUpdate);
        updateLock.unlock();
    }

    public void removeEndedMatch(Match match) {
        runningMatchesMutex.lock();
        runningMatches.remove(match.getMatchId());
        runningMatchesMutex.unlock();
    }

    public void makePlayerMove(long playerId, long matchId, Direction direction) {
        inputLock.lock();
        Match match = getRunningMatch(matchId);
        Player player = match.getPlayerById(playerId);
        player.setDirection(direction);
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
        player.setTrace(new ArrayList<>());
        return player;
    }

    private Match createNewMatch(int numberOfPlayers) {
        Match match = new Match();
        match.setMatchId(MATCH_ID_COUNTER);
        MATCH_ID_COUNTER += 1;
        match.setPlayers(new LinkedList<>());
        match.setNumberOfPlayers(numberOfPlayers);
        match.setState(MatchState.WAITING);
        return match;
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
}
