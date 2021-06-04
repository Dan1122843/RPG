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
    public static Enemy enemy;
    public static int potionCount = 3;
    public static String fastestTimeString;
    public static int fastestTime;
    public static String fastestName;

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

                    gui.getStatsLabel().setText("<html><b>Player Name: </b>" + p1.getName()
                            + "<b>&emsp;Health: </b>" + p1.getHealth() + " HP<b>&emsp;Attack: </b>"
                            + p1.getAttack() + " DMG<b>&emsp;Potions: </b>" + potionCount + "</html>");
                    gui.getObjectiveLabel().setText("<html>Find your way out of the dungeon. Defeat all the enemies: <br/><br/>" + fastestPlayerString + "<html/>");
                    gui.getStartPageFrame().setVisible(false);
                    gui.getGameplayFrame().setVisible(true);
                    timer.startTimer();
                }

            });

            gui.getRight().addActionListener((ActionEvent e) -> {
                enemy = gui.checkForEnemy("right");
            });

            gui.getLeft().addActionListener((ActionEvent e) -> {
                enemy = gui.checkForEnemy("left");
            });

            gui.getAttack().addActionListener((ActionEvent e) -> {
                //Code for when the player presses attack
                enemy.setHealth(enemy.getHealth() - p1.getAttack());
                p1.setHealth(p1.getHealth() - enemy.getAttack());
                gui.getStatsLabel().setText("<html><b>Player Name: </b>" + p1.getName()
                        + "<b>&emsp;Health: </b>" + p1.getHealth() + " HP<b>&emsp;Attack: </b>"
                        + p1.getAttack() + " DMG<b>&emsp;Potions: </b>" + potionCount + "</html>");

                gui.getEnemyStatsLabel().setText("<html><b>Enemy Name: </b>" + enemy.getName()
                        + "<b>&emsp;Health: </b>" + enemy.getHealth()
                        + " HP<b>&emsp;Attack: </b>" + enemy.getAttack()
                        + " DMG<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>"
                        + "&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<b>You deal " + p1.getAttack()
                        + "DMG to the enemy.<br/><br/>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;" + enemy.getName()
                        + " deals " + enemy.getAttack() + "DMG to you.</b></html>");

                if (enemy.getHealth() <= 0) {
                    if (enemy.getName().equals("OrcBoss")) {
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

                        // Write the players time to the time file
                        database.insertPlayerData(p1.getName(), currentTime);

                        database.closeConnections();

                        gui.getEnemyStatsLabel().setText(timeString);
                        gui.gameWon();
                    } else {
                        gui.battleWon();
                    }

                } else if (p1.getHealth() <= 0) {
                    gui.battleLost();
                }
            });

            gui.getHeal().addActionListener((ActionEvent e) -> {
                //Code for when the player presses heal
                if (potionCount > 0) {
                    if (isWarrior) {
                        if (p1.getHealth() != 100) {
                            potionCount -= 1;
                            if (potionCount == 1) {
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
                            gui.getEnemyStatsLabel().setText("<html><b>Enemy Name: </b>"
                                    + enemy.getName() + "<b>&emsp;Health: </b>"
                                    + enemy.getHealth() + " HP<b>&emsp;Attack: </b>"
                                    + enemy.getAttack() + " DMG" + "<br/><br/><br/><br/>"
                                    + "<br/><br/><br/><br/><br/><br/>&emsp;&emsp;"
                                    + "&emsp;&emsp;&emsp;&emsp;<b>You are already at max health</b></html>");
                        }
                    }
                    if (isDwarf) {
                        if (p1.getHealth() != 125) {
                            potionCount -= 1;
                            if (potionCount == 1) {
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
                            gui.getEnemyStatsLabel().setText("<html><b>Enemy Name: </b>"
                                    + enemy.getName() + "<b>&emsp;Health: </b>"
                                    + enemy.getHealth() + " HP<b>&emsp;Attack: </b>"
                                    + enemy.getAttack() + " DMG" + "<br/><br/><br/><br/>"
                                    + "<br/><br/><br/><br/><br/><br/>&emsp;&emsp;"
                                    + "&emsp;&emsp;&emsp;&emsp;<b>You are already at max health</b></html>");
                        }
                    }
                    if (isElf) {
                        if (p1.getHealth() != 75) {
                            potionCount -= 1;
                            if (potionCount == 1) {
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
                            gui.getEnemyStatsLabel().setText("<html><b>Enemy Name: </b>"
                                    + enemy.getName() + "<b>&emsp;Health: </b>"
                                    + enemy.getHealth() + " HP<b>&emsp;Attack: </b>"
                                    + enemy.getAttack() + " DMG" + "<br/><br/><br/><br/>"
                                    + "<br/><br/><br/><br/><br/><br/>&emsp;&emsp;"
                                    + "&emsp;&emsp;&emsp;&emsp;<b>You are already at max health</b></html>");
                        }
                    }

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
