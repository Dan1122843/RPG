package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import rpgCharacters.*;
import rpgDatabase.*;

/**
 *
 * @author Liam O'Connor ID: 18048495
 */
public class Main {

    public static boolean bossDefeated = false;
    public static boolean inBattle = false;
    public static boolean isWarrior = false;
    public static boolean isDwarf = false;
    public static boolean isElf = false;
    public static Player p1;
    public static int potionCount = 3;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            GameSetup gui = new GameSetup();

            FastestTimeDB database = new FastestTimeDB();
            database.establishConnection();
            database.createTable();

            //Creates an instance of the timer class
            Timer timer = new Timer();
            database.transferFastestTimeData();

            //Gets the fastest time from the times file
            int fastestTime = database.getFastestTime();
            String FastestName = database.getFastestPlayerName();
            String fastestTimeString = "There is currently no fastest time. Good luck and be quick!";
            String fastestPlayerString;
            // If the fastest time is the default set time (Means no times are in the times file)
            if (fastestTime == 1000000) {
                fastestPlayerString = fastestTimeString;
                // If there is a valid fastest time, display the fastest time
            } else {
                fastestTimeString = ConvertSecondsToTimeString(fastestTime);
                fastestPlayerString = "The fastest time to beat is " + fastestTimeString + " set by " + FastestName + ". Do you think you can beat it?";
            }

            gui.getWarriorButton().addActionListener((ActionEvent e) -> {
                gui.getStartPageCharacterButtonsPanel().setVisible(false);
                gui.getStartPageNamePanel().setVisible(true);
                GameSetup.setCharacter(1);
                isWarrior = true;
            });

            gui.getElfButton().addActionListener((ActionEvent e) -> {
                gui.getStartPageCharacterButtonsPanel().setVisible(false);
                gui.getStartPageNamePanel().setVisible(true);
                GameSetup.setCharacter(2);
                isElf = true;
            });

            gui.getDwarfButton().addActionListener((ActionEvent e) -> {
                gui.getStartPageCharacterButtonsPanel().setVisible(false);
                gui.getStartPageNamePanel().setVisible(true);
                GameSetup.setCharacter(3);
                isDwarf = true;
            });

            gui.getEnterButton().addActionListener((ActionEvent e) -> {
                String PlayerName = gui.getNameTextBox().getText();

                if (PlayerName.equals("")) {
                    gui.getNameLabel().setText("You must enter a name!");
                    gui.getNameLabel().setForeground(Color.red);
                } else {
                    if (isWarrior) {
                        p1 = new Warrior(PlayerName);
                    }
                    if (isElf) {
                        p1 = new Elf(PlayerName);
                    }
                    if (isDwarf) {
                        p1 = new Dwarf(PlayerName);
                    }

                    gui.getStatsLabel().setText("<html><b>Player Name: </b>" + p1.getName() + "<b>&emsp;Health: </b>"
                            + p1.getHealth() + " HP<b>&emsp;Attack: </b>" + p1.getAttack() + " DMG<b>&emsp;Potions: </b>" + potionCount + "</html>");
                    gui.getObjectiveLabel().setText("<html>Find your way out of the dungeon. Defeat all the enemies: <br/><br/>" + fastestPlayerString + "<html/>");
                    gui.getStartPageFrame().setVisible(false);
                    gui.getGameplayFrame().setVisible(true);
                    timer.startTimer();
                }

            });
            
            gui.getRight().addActionListener((ActionEvent e) -> {
            
                Enemy enemy = gui.checkForEnemy();
                if(enemy != null){
                    //Call attack method with p1 and enemy
                }
            });
            
            gui.getLeft().addActionListener((ActionEvent e) -> {
            
                Enemy enemy = gui.checkForEnemy();
                if(enemy != null){
                    //Call attack method with p1 and enemy
                }
            });

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

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
