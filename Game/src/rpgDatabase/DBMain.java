package rpgDatabase;

/**
 *
 * @author danielwillis
 */
public class DBMain {

    public static void main(String[] args) throws InterruptedException {
        FastestTimeDB database = new FastestTimeDB();
        database.establishConnection();
        database.createTable();

        //Creates an instance of the timer class
        Timer timer = new Timer();
        database.transferFastestTimeData();

        //Gets the fastest time from the times file
        int fastestTime = database.getFastestTime();
        String FastestName = database.getFastestPlayerName();
        String fastestTimeString = "No Fastest Time";
        // If the fastest time is the default set time (Means no times are in the times file)
        if (fastestTime == 1000000) {
            System.out.println("There is currently no fastest time. Good luck and be quick!");
            // If there is a valid fastest time, display the fastest time
        } else {
            fastestTimeString = ConvertSecondsToTimeString(fastestTime);
            System.out.println("The fastest time to beat is " + fastestTimeString + " set by " + FastestName + ". Do you think you can beat it?");
        }

        /*
        !!!
        Get the players name from the textbox on the starting screen
        !!!
         */
        String name = "Bob";

        // Start the timer
        timer.startTimer();

        //Play the game
        System.out.println("Sleeping for 200 seconds");
        Thread.sleep(200000);

        //When the orcboss is defeated (OrcBoss.isDefeated == True), Run all of the below code
        System.out.println("Congratulations you have defeated the Boss!\n");

        // Stop the timer
        timer.endTimer();

        // Get the players time in seconds
        int currentTime = timer.getSeconds();

        //If the player set the first valid time
        if (currentTime < fastestTime && fastestTime == 1000000) {
            System.out.println("You now hold the fastest time!");
            // If the player beat the fastest valid time
        } else if (currentTime < fastestTime && fastestTime != 1000000) {
            System.out.println("You now hold the fastest time! The previous fastest time was " + fastestTimeString);
            // If the player tied with the fastest time
        } else if (currentTime == fastestTime) {
            System.out.println("You tied with the fastest time!");
            // If the player did not beat the fastest time
        } else {
            System.out.println("The all time fastest time was " + fastestTimeString);
        }

        // Display the players time
        System.out.println("Your time was " + ConvertSecondsToTimeString(currentTime));

        // Write the players time to the time file
        database.insertPlayerData(name, currentTime);

        database.closeConnections();
    }

    /**
     * Converts seconds to a string that displays minutes and seconds in a
     * readable format
     *
     * @param seconds Takes in the seconds as an int
     * @return Returns a formatted string detailing the minutes and seconds
     */
    public static String ConvertSecondsToTimeString(int seconds) {
        String timeElapsed;
        // Calculate the amount of minutes from the seconds given
        int minutes = seconds / 60;
        // Subtract the minutes from total seconds so the seconds stay under 60
        seconds = seconds - (minutes * 60);

        // Display different time taken strings depending on if the values are plurals
        if (minutes == 1 && seconds == 1) {
            timeElapsed = minutes + " minute, " + seconds + " second";
        } else if (minutes == 1) {
            timeElapsed = minutes + " minute, " + seconds + " seconds";
        } else if (seconds == 1) {
            timeElapsed = minutes + " minutes, " + seconds + " second";
        } else {
            timeElapsed = minutes + " minutes, " + seconds + " seconds";
        }

        return timeElapsed;
    }

}
