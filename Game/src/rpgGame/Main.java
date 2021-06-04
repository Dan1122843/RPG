package rpgGame;

import rpgGUI.GameSetup;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import rpgCharacters.*;
import rpgDatabase.*;

/**
 * Main class runs the game functions
 * @author Liam O'Connor, Daniel Willis, Aidan Lacey ID: 18048495, 19079994,
 * 17994308
 */
public class Main {

    // Initialise class attributes
    public static boolean bossDefeated = false;
    public static boolean inBattle = false;
    public static boolean isWarrior = false;
    public static boolean isDwarf = false;
    public static boolean isElf = false;
    public static Player p1;
    public static Enemy enemy;
    public static int potionCount = 3;
    public static String fastestTimeString;
    public static int fastestTime;
    public static String fastestName;

    /**
     * Main method which runs the game
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //Sets up the gui elements
            GameSetup gui = new GameSetup();

            //Creates a database object
            FastestTimeDB database = new FastestTimeDB();
            //Connects to the database
            database.establishConnection();
            //Creates the database table if not already made
            database.createTable();

            //Creates an instance of the timer class
            Timer timer = new Timer();
            //Transfers all the data from the table to attributes
            database.transferFastestTimeData();

            //Gets the fastest time details from the times file
            fastestTime = database.getFastestTime();
            fastestName = database.getFastestPlayerName();
            fastestTimeString = "There is currently no fastest time. Good luck and be quick!";
            String fastestPlayerString;
            // If the fastest time is the default set time (Means no times are in the times file)
            if (fastestTime == 1000000) {
                fastestPlayerString = fastestTimeString;
                // If there is a valid fastest time, display the fastest time
            } else {
                fastestTimeString = ConvertSecondsToTimeString(fastestTime);
                fastestPlayerString = "The fastest time to beat is " + fastestTimeString + " set by " + fastestName + ". Do you think you can beat it?";
            }

            //If the warrior button is pressed, progress through the gui and set player as a warrior
            gui.getWarriorButton().addActionListener((ActionEvent e) -> {
                gui.getStartPageCharacterButtonsPanel().setVisible(false);
                gui.getStartPageNamePanel().setVisible(true);
                GameSetup.setCharacter(1);
                isWarrior = true;
            });

            //If the elf button is pressed, progress through the gui and set player as an elf
            gui.getElfButton().addActionListener((ActionEvent e) -> {
                gui.getStartPageCharacterButtonsPanel().setVisible(false);
                gui.getStartPageNamePanel().setVisible(true);
                GameSetup.setCharacter(2);
                isElf = true;
            });

            //If the dwarf button is pressed, progress through the gui and set player as an dwarf
            gui.getDwarfButton().addActionListener((ActionEvent e) -> {
                gui.getStartPageCharacterButtonsPanel().setVisible(false);
                gui.getStartPageNamePanel().setVisible(true);
                GameSetup.setCharacter(3);
                isDwarf = true;
            });

            //If the enter button is pressed, check a name has been entered and progress to the gameplay frame
            gui.getEnterButton().addActionListener((ActionEvent e) -> {
                //Get the players name from the textbox
                String PlayerName = gui.getNameTextBox().getText();

                //Check the player has entered a name
                if (PlayerName.equals("")) {
                    gui.getNameLabel().setText("You must enter a name!");
                    gui.getNameLabel().setForeground(Color.red);
                    //If they have entered a name, create a player object for the users class
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

                    //Update the visible labels to represent the users player
                    gui.getStatsLabel().setText("<html><b>Player Name: </b>" + p1.getName()
                            + "<b>&emsp;Health: </b>" + p1.getHealth() + " HP<b>&emsp;Attack: </b>"
                            + p1.getAttack() + " DMG<b>&emsp;Potions: </b>" + potionCount + "</html>");
                    gui.getObjectiveLabel().setText("<html>Find your way out of the dungeon. "
                            + "Defeat all the enemies: <br/><br/>" + fastestPlayerString + "<html/>");
                    //Close the starting frame and make the gameplay frame visible
                    gui.getStartPageFrame().setVisible(false);
                    gui.getGameplayFrame().setVisible(true);
                    //Start the timer
                    timer.startTimer();
                }

            });

            //If the user presses the right button, check for an enemy and move the player right
            gui.getRight().addActionListener((ActionEvent e) -> {
                enemy = gui.checkForEnemy("right");
            });

            //If the user presses the left button, check for an enemy and move the player left
            gui.getLeft().addActionListener((ActionEvent e) -> {
                enemy = gui.checkForEnemy("left");
            });

            //If the user presses the attack button
            gui.getAttack().addActionListener((ActionEvent e) -> {
                //Change the enemy and the players health accordingly
                enemy.setHealth(enemy.getHealth() - p1.getAttack());
                p1.setHealth(p1.getHealth() - enemy.getAttack());
                //Update the player stats label to represent the change
                gui.getStatsLabel().setText("<html><b>Player Name: </b>" + p1.getName()
                        + "<b>&emsp;Health: </b>" + p1.getHealth() + " HP<b>&emsp;Attack: </b>"
                        + p1.getAttack() + " DMG<b>&emsp;Potions: </b>" + potionCount + "</html>");

                //Update the enemy stats label to represent the change
                gui.getEnemyStatsLabel().setText("<html><b>Enemy Name: </b>" + enemy.getName()
                        + "<b>&emsp;Health: </b>" + enemy.getHealth()
                        + " HP<b>&emsp;Attack: </b>" + enemy.getAttack()
                        + " DMG<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>"
                        + "&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<b>You deal " + p1.getAttack()
                        + "DMG to the enemy.<br/><br/>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;" + enemy.getName()
                        + " deals " + enemy.getAttack() + "DMG to you.</b></html>");

                //If the enemy has no more health
                if (enemy.getHealth() <= 0) {
                    //If the last enemy has been defeated
                    if (enemy.getName().equals("OrcBoss")) {
                        //Stop the timer
                        timer.endTimer();

                        // Get the players time in seconds
                        int currentTime = timer.getSeconds();
                        String timeString;

                        //If the player set the first valid time
                        if (currentTime < fastestTime && fastestTime == 1000000) {
                            timeString = "<html><b>You now hold the fastest time!";
                            // If the player beat the fastest valid time
                        } else if (currentTime < fastestTime && fastestTime != 1000000) {
                            timeString = "<html><b>You now hold the fastest time!<br/> The previous fastest time was " + fastestTimeString + " set by " + fastestName + ".";
                            // If the player tied with the fastest time
                        } else if (currentTime == fastestTime) {
                            timeString = "<html><b>You tied with the fastest time!";
                            // If the player did not beat the fastest time
                        } else {
                            timeString = "<html><b>The all time fastest time was " + fastestTimeString + " set by " + fastestName + ".";
                        }

                        // Display the players time
                        timeString += "<br/><br/>Your time was "
                                + ConvertSecondsToTimeString(currentTime)
                                + "<br/><br/><br/><br/><br/><br/><br/>"
                                + "&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"
                                + "You dealt " + p1.getAttack() + "DMG to the boss."
                                + "<br/><br/>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"
                                + "You Defeated the Boss!<br/><br/>&emsp;&emsp;&emsp;&emsp;"
                                + "&emsp;&emsp;Congratulations!</b></html>";

                        // Write the players time to the times table
                        database.insertPlayerData(p1.getName(), currentTime);

                        //Close the database connection
                        database.closeConnections();

                        //Update the enemy label with the time text
                        gui.getEnemyStatsLabel().setText(timeString);
                        //Call the game won method
                        gui.gameWon();
                    } else {
                        //Call the battle won function
                        gui.battleWon();
                    }
                //If the player has run out of health, call the battle lost method
                } else if (p1.getHealth() <= 0) {
                    gui.battleLost();
                }
            });

            //If the user presses the heal button
            gui.getHeal().addActionListener((ActionEvent e) -> {
                //If the player has potions
                if (potionCount > 0) {
                    //If the players class is a warrior
                    if (isWarrior) {
                        //If the player doesn't have max health
                        if (p1.getHealth() != 100) {
                            //Take away a potion and update the players health
                            potionCount -= 1;
                            //Display correct grammar depending on if the player has a potion or potions
                            if (potionCount == 1) {
                                //Set the players health to max if the number would be above their max health
                                if (p1.getHealth() >= 80) {
                                    p1.setHealth(100);
                                    gui.getEnemyStatsLabel().setText("<html><b>Enemy Name: </b>"
                                            + enemy.getName() + "<b>&emsp;Health: </b>"
                                            + enemy.getHealth() + " HP<b>&emsp;Attack: </b>"
                                            + enemy.getAttack() + " DMG" + "<br/><br/><br/><br/>"
                                            + "<br/><br/><br/><br/><br/><br/>&emsp;&emsp;"
                                            + "&emsp;&emsp;&emsp;&emsp;<b>You heal to 100HP<br/><br/>&emsp;&emsp;"
                                            + "&emsp;&emsp;&emsp;&emsp;You now have 1 potion</b></html>");
                                } else {
                                    //Set the players health to +25 of what they have
                                    p1.setHealth(p1.getHealth() + 25);
                                    gui.getEnemyStatsLabel().setText("<html><b>Enemy Name: </b>"
                                            + enemy.getName() + "<b>&emsp;Health: </b>"
                                            + enemy.getHealth() + " HP<b>&emsp;Attack: </b>"
                                            + enemy.getAttack() + " DMG" + "<br/><br/><br/><br/>"
                                            + "<br/><br/><br/><br/><br/><br/>&emsp;&emsp;"
                                            + "&emsp;&emsp;&emsp;&emsp;<b>You heal to " + p1.getHealth() + "HP<br/><br/>&emsp;&emsp;"
                                            + "&emsp;&emsp;&emsp;&emsp;You now have 1 potion</b></html>");
                                }
                            } else {
                                //Set the players health to max if the number would be above their max health
                                if (p1.getHealth() >= 80) {
                                    p1.setHealth(100);
                                    gui.getEnemyStatsLabel().setText("<html><b>Enemy Name: </b>"
                                            + enemy.getName() + "<b>&emsp;Health: </b>"
                                            + enemy.getHealth() + " HP<b>&emsp;Attack: </b>"
                                            + enemy.getAttack() + " DMG" + "<br/><br/><br/><br/>"
                                            + "<br/><br/><br/><br/><br/><br/>&emsp;&emsp;"
                                            + "&emsp;&emsp;&emsp;&emsp;<b>You heal to 100HP<br/><br/>&emsp;&emsp;"
                                            + "&emsp;&emsp;&emsp;&emsp;You now have " + potionCount + " potions</b></html>");
                                } else {
                                    //Set the players health to +25 of what they have
                                    p1.setHealth(p1.getHealth() + 25);
                                    gui.getEnemyStatsLabel().setText("<html><b>Enemy Name: </b>"
                                            + enemy.getName() + "<b>&emsp;Health: </b>"
                                            + enemy.getHealth() + " HP<b>&emsp;Attack: </b>"
                                            + enemy.getAttack() + " DMG" + "<br/><br/><br/><br/>"
                                            + "<br/><br/><br/><br/><br/><br/>&emsp;&emsp;"
                                            + "&emsp;&emsp;&emsp;&emsp;<b>You heal to " + p1.getHealth() + "HP<br/><br/>&emsp;&emsp;"
                                            + "&emsp;&emsp;&emsp;&emsp;You now have " + potionCount + " potions</b></html>");
                                }
                            }
                        } else {
                            //Display that the player already has max health
                            gui.getEnemyStatsLabel().setText("<html><b>Enemy Name: </b>"
                                    + enemy.getName() + "<b>&emsp;Health: </b>"
                                    + enemy.getHealth() + " HP<b>&emsp;Attack: </b>"
                                    + enemy.getAttack() + " DMG" + "<br/><br/><br/><br/>"
                                    + "<br/><br/><br/><br/><br/><br/>&emsp;&emsp;"
                                    + "&emsp;&emsp;&emsp;&emsp;<b>You are already at max health</b></html>");
                        }
                    }
                    if (isDwarf) {
                        //If the player doesn't have max health
                        if (p1.getHealth() != 125) {
                            //Take away a potion and update the players health
                            potionCount -= 1;
                            //Display correct grammar depending on if the player has a potion or potions
                            if (potionCount == 1) {
                                //Set the players health to max if the number would be above their max health
                                if (p1.getHealth() >= 100) {
                                    p1.setHealth(125);
                                    gui.getEnemyStatsLabel().setText("<html><b>Enemy Name: </b>"
                                            + enemy.getName() + "<b>&emsp;Health: </b>"
                                            + enemy.getHealth() + " HP<b>&emsp;Attack: </b>"
                                            + enemy.getAttack() + " DMG" + "<br/><br/><br/><br/>"
                                            + "<br/><br/><br/><br/><br/><br/>&emsp;&emsp;"
                                            + "&emsp;&emsp;&emsp;&emsp;<b>You heal to 125HP<br/><br/>&emsp;&emsp;"
                                            + "&emsp;&emsp;&emsp;&emsp;You now have 1 potion</b></html>");
                                } else {
                                    //Set the players health to +25 of what they have
                                    p1.setHealth(p1.getHealth() + 25);
                                    gui.getEnemyStatsLabel().setText("<html><b>Enemy Name: </b>"
                                            + enemy.getName() + "<b>&emsp;Health: </b>"
                                            + enemy.getHealth() + " HP<b>&emsp;Attack: </b>"
                                            + enemy.getAttack() + " DMG" + "<br/><br/><br/><br/>"
                                            + "<br/><br/><br/><br/><br/><br/>&emsp;&emsp;"
                                            + "&emsp;&emsp;&emsp;&emsp;<b>You heal to " + p1.getHealth() + "HP<br/><br/>&emsp;&emsp;"
                                            + "&emsp;&emsp;&emsp;&emsp;You now have 1 potion</b></html>");
                                }
                            } else {
                                //Set the players health to max if the number would be above their max health
                                if (p1.getHealth() >= 100) {
                                    p1.setHealth(125);
                                    gui.getEnemyStatsLabel().setText("<html><b>Enemy Name: </b>"
                                            + enemy.getName() + "<b>&emsp;Health: </b>"
                                            + enemy.getHealth() + " HP<b>&emsp;Attack: </b>"
                                            + enemy.getAttack() + " DMG" + "<br/><br/><br/><br/>"
                                            + "<br/><br/><br/><br/><br/><br/>&emsp;&emsp;"
                                            + "&emsp;&emsp;&emsp;&emsp;<b>You heal to 125HP<br/><br/>&emsp;&emsp;"
                                            + "&emsp;&emsp;&emsp;&emsp;You now have " + potionCount + " potions</b></html>");
                                } else {
                                    //Set the players health to +25 of what they have
                                    p1.setHealth(p1.getHealth() + 25);
                                    gui.getEnemyStatsLabel().setText("<html><b>Enemy Name: </b>"
                                            + enemy.getName() + "<b>&emsp;Health: </b>"
                                            + enemy.getHealth() + " HP<b>&emsp;Attack: </b>"
                                            + enemy.getAttack() + " DMG" + "<br/><br/><br/><br/>"
                                            + "<br/><br/><br/><br/><br/><br/>&emsp;&emsp;"
                                            + "&emsp;&emsp;&emsp;&emsp;<b>You heal to " + p1.getHealth() + "HP<br/><br/>&emsp;&emsp;"
                                            + "&emsp;&emsp;&emsp;&emsp;You now have " + potionCount + " potions</b></html>");
                                }
                            }
                        } else {
                            //Display that the player already has max health
                            gui.getEnemyStatsLabel().setText("<html><b>Enemy Name: </b>"
                                    + enemy.getName() + "<b>&emsp;Health: </b>"
                                    + enemy.getHealth() + " HP<b>&emsp;Attack: </b>"
                                    + enemy.getAttack() + " DMG" + "<br/><br/><br/><br/>"
                                    + "<br/><br/><br/><br/><br/><br/>&emsp;&emsp;"
                                    + "&emsp;&emsp;&emsp;&emsp;<b>You are already at max health</b></html>");
                        }
                    }
                    if (isElf) {
                        //If the player doesn't have max health
                        if (p1.getHealth() != 75) {
                            //Take away a potion and update the players health
                            potionCount -= 1;
                            //Display correct grammar depending on if the player has a potion or potions
                            if (potionCount == 1) {
                                //Set the players health to max if the number would be above their max health
                                if (p1.getHealth() >= 50) {
                                    p1.setHealth(75);
                                    gui.getEnemyStatsLabel().setText("<html><b>Enemy Name: </b>"
                                            + enemy.getName() + "<b>&emsp;Health: </b>"
                                            + enemy.getHealth() + " HP<b>&emsp;Attack: </b>"
                                            + enemy.getAttack() + " DMG" + "<br/><br/><br/><br/>"
                                            + "<br/><br/><br/><br/><br/><br/>&emsp;&emsp;"
                                            + "&emsp;&emsp;&emsp;&emsp;<b>You heal to 75HP<br/><br/>&emsp;&emsp;"
                                            + "&emsp;&emsp;&emsp;&emsp;You now have 1 potion</b></html>");
                                } else {
                                    //Set the players health to +25 of what they have
                                    p1.setHealth(p1.getHealth() + 25);
                                    gui.getEnemyStatsLabel().setText("<html><b>Enemy Name: </b>"
                                            + enemy.getName() + "<b>&emsp;Health: </b>"
                                            + enemy.getHealth() + " HP<b>&emsp;Attack: </b>"
                                            + enemy.getAttack() + " DMG" + "<br/><br/><br/><br/>"
                                            + "<br/><br/><br/><br/><br/><br/>&emsp;&emsp;"
                                            + "&emsp;&emsp;&emsp;&emsp;<b>You heal to " + p1.getHealth() + "HP<br/><br/>&emsp;&emsp;"
                                            + "&emsp;&emsp;&emsp;&emsp;You now have 1 potion</b></html>");
                                }
                            } else {
                                //Set the players health to max if the number would be above their max health
                                if (p1.getHealth() >= 50) {
                                    p1.setHealth(75);
                                    gui.getEnemyStatsLabel().setText("<html><b>Enemy Name: </b>"
                                            + enemy.getName() + "<b>&emsp;Health: </b>"
                                            + enemy.getHealth() + " HP<b>&emsp;Attack: </b>"
                                            + enemy.getAttack() + " DMG" + "<br/><br/><br/><br/>"
                                            + "<br/><br/><br/><br/><br/><br/>&emsp;&emsp;"
                                            + "&emsp;&emsp;&emsp;&emsp;<b>You heal to 75HP<br/><br/>&emsp;&emsp;"
                                            + "&emsp;&emsp;&emsp;&emsp;You now have " + potionCount + " potions</b></html>");
                                } else {
                                    //Set the players health to +25 of what they have
                                    p1.setHealth(p1.getHealth() + 25);
                                    gui.getEnemyStatsLabel().setText("<html><b>Enemy Name: </b>"
                                            + enemy.getName() + "<b>&emsp;Health: </b>"
                                            + enemy.getHealth() + " HP<b>&emsp;Attack: </b>"
                                            + enemy.getAttack() + " DMG" + "<br/><br/><br/><br/>"
                                            + "<br/><br/><br/><br/><br/><br/>&emsp;&emsp;"
                                            + "&emsp;&emsp;&emsp;&emsp;<b>You heal to " + p1.getHealth() + "HP<br/><br/>&emsp;&emsp;"
                                            + "&emsp;&emsp;&emsp;&emsp;You now have " + potionCount + " potions</b></html>");
                                }
                            }
                        } else {
                            //Display that the player already has max health
                            gui.getEnemyStatsLabel().setText("<html><b>Enemy Name: </b>"
                                    + enemy.getName() + "<b>&emsp;Health: </b>"
                                    + enemy.getHealth() + " HP<b>&emsp;Attack: </b>"
                                    + enemy.getAttack() + " DMG" + "<br/><br/><br/><br/>"
                                    + "<br/><br/><br/><br/><br/><br/>&emsp;&emsp;"
                                    + "&emsp;&emsp;&emsp;&emsp;<b>You are already at max health</b></html>");
                        }
                    }

                    //Update the players stats to represent the health increase
                    gui.getStatsLabel().setText("<html><b>Player Name: </b>" + p1.getName()
                            + "<b>&emsp;Health: </b>" + p1.getHealth() + " HP<b>&emsp;Attack: </b>"
                            + p1.getAttack() + " DMG<b>&emsp;Potions: </b>" + potionCount + "</html>");
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
