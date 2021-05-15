package rpgFileIO;

import java.io.*;

/**
 * Creates, reads and writes to the file where the times are stored and returns the fastest
 * @author Liam O'Connor, Daniel Willis, Aidan Lacey ID: 18048495, 19079994,
 * 17994308
 */

public class FastestTimesFile {

    public File file;

    /**
     * Create the fastest times file
     */
    public FastestTimesFile() {
        file = new File("fastestTimes.txt");
    }

    /**
     * Read the fastest times file and return the fastest time
     * @return Returns the fastest time as an int
     */
    public int getFastestTime() {

        int fastestTime = 1000000; //11.57 days

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))){
                String line = reader.readLine();
                // While the file hasn't been entirely read, read the scores in the file
                while (line != null) {
                    try {
                        int time = Integer.parseInt(line.trim());
                        if (time < fastestTime) {
                            fastestTime = time;
                        }
                    } catch (NumberFormatException e1) {
                        // Ignore invalid scores
                        System.err.println(e1);
                        System.err.println("Invalid time: " + line);
                    }
                    line = reader.readLine();
                }
                reader.close();

            } catch (IOException ex) {
                System.err.println("ERROR reading times from file");
            }
        }

        return fastestTime;
    }

    /**
     * Writes the users time to the fastest times file
     * @param time Takes in the users time as an int 
     */
    public void writeTime(int time) {
        try (BufferedWriter output = new BufferedWriter(new FileWriter(file, true))){
            if (file.length() != 0) {
                output.newLine();
            }
            output.append("" + time);
            output.close();

        } catch (IOException e) {
            System.out.printf("Error writing score to file: %s\n", e);
        }
    }
}
