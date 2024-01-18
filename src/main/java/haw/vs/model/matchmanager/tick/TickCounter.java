package haw.vs.model.matchmanager.tick;

public class TickCounter {
    private static long TICK_COUNTER = 0;

    public synchronized static long getTickCounter() {
        return TICK_COUNTER;
    }

    public synchronized static void incrementTickCounter() {
        TICK_COUNTER += 1;
    }
}
