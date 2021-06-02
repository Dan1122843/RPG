package rpgFileIO;

import java.time.Instant;
import java.time.Duration;

/**
 * The Timer class records the start and end times, storing the total time in
 * seconds
 *
 * @author Liam O'Connor, Daniel Willis, Aidan Lacey ID: 18048495, 19079994,
 * 17994308
 */
public class Timer {

    public Instant start;
    private int seconds;

    public Timer() {
        seconds = 0;
    }

    /**
     * Class marks the time at start
     */
    public void startTimer() {
        start = Instant.now();
    }

    /**
     * Class marks the time at end and calculates the differences and stores in
     * seconds
     */
    public void endTimer() {
        Instant end = Instant.now();

        seconds = (int) (Duration.between(start, end).toMillis() / 1000);

    }

    /**
     * Getter for the seconds variable
     *
     * @return Returns the int value stored in seconds
     */
    public int getSeconds() {
        return seconds;
    }

}
