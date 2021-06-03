package rpggame;

import rpgFileIO.FastestTimesFile;
import rpgDatabase.Timer;
import java.util.Random;
import rpgMap.*;
import rpgCharacters.*;
import java.util.Scanner;

/**
 *
 * @author Liam O'Connor, Daniel Willis, Aidan Lacey ID: 18048495, 19079994,
 * 17994308
 */
public class Main {

    public static Scanner scan = new Scanner(System.in);
    public static boolean bossDefeated = false;
    public static boolean inBattle = false;
    public static boolean isWarrior = false;
    public static boolean isDwarf = false;
    public static boolean isElf = false;

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Map map = new Map();
        int playerPotionCount = 3;
        char input = ' ';
        Enemy enemies[] = new Enemy[11];

        //Welcome user and create their character
        System.out.println("Welcome to The RPG\n");

        // Create character
        Player p1 = CreateCharacter();

        // Welcoming message
        System.out.println("\nWelcome " + p1.getName() + " to the rpg world of monsters. \n");
        // Display Character stats
        System.out.println("Character stats: \nHP: " + p1.getHealth() + "\nAttack: " + p1.getAttack() + "\n");

        // Generate the enemies and their locations on the map
        Random rand = new Random();
        int xPosition;
        int[] occupiedPositions = new int[3];

        // Generate 3 Demons
        for (int i = 0; i < 3; i++) {
            xPosition = 1;
            for (int j = 0; j < 3; j++) {
                while (occupiedPositions[j] == xPosition || xPosition == 0 || xPosition == 1) {
                    xPosition = rand.nextInt((16) + 2);
                }
            }
            enemies[i] = new Demon(xPosition);
            occupiedPositions[i] = xPosition;
        }
        // Generate 3 Spiders
        for (int i = 3; i < 6; i++) {
            xPosition = 0;
            for (int j = 0; j < 3; j++) {
                while (occupiedPositions[j] == xPosition || xPosition == 0 || xPosition == 1) {
                    xPosition = rand.nextInt((16) + 2);
                }
            }
            enemies[i] = new Spider(xPosition);
            occupiedPositions[i - 3] = xPosition;
        }
        // Generate 2 Vampires
        for (int i = 6; i < 8; i++) {
            xPosition = 0;
            for (int j = 0; j < 2; j++) {
                while (occupiedPositions[j] == xPosition || xPosition == 0 || xPosition == 1) {
                    xPosition = rand.nextInt((16) + 2);
                    //modified for potion. This doesn't work at the moment
                    if (xPosition == 2) {
                        break;
                    }
                }
            }
            enemies[i] = new Vampire(xPosition);
            occupiedPositions[i - 6] = xPosition;
        }
        // Generate 2 Giants
        for (int i = 8; i < 10; i++) {
            xPosition = 0;
            for (int j = 0; j < 2; j++) {
                while (occupiedPositions[j] == xPosition || xPosition == 0 || xPosition == 1) {
                    xPosition = rand.nextInt((16) + 2);
                    //modified for potion. This doesn't work at the moment
                    if (xPosition == 17) {
                        break;
                    } else if (xPosition == 2) {
                        break;
                    }
                }
            }
            enemies[i] = new Giant(xPosition);
            occupiedPositions[i - 8] = xPosition;
        }
        //Generate the boss
        enemies[10] = new OrcBoss();

        // Generate the map including the player and enemies locations
        map.generateMapLayout();
        map.putPotionsOnMap();
        map.putPlayerOnMap(p1.getLevel(), p1.getxPosition());
        for (int i = 0; i < 11; i++) {
            map.putEnemiesOnMap(enemies[i].getLevel(), enemies[i].getxPosition(), enemies[i].getName());
        }

        // Create a times file to store the times
        FastestTimesFile timesFile = new FastestTimesFile();
        //Creates an instance of the timer class
        Timer timer = new Timer();
        //Gets the fastest time from the times file
        int fastestTime = timesFile.getFastestTime();
        String fastestTimeString = "";
        // If the fastest time is the default set time (Means no times are in the times file)
        if (fastestTime == 1000000) {
            System.out.println("There is currently no fastest time. Good luck and be quick!");
            // If there is a valid fastest time, display the fastest time
        } else {
            fastestTimeString = ConvertSecondsToTimeString(fastestTime);
            System.out.println("The fastest time to beat is " + fastestTimeString + ". Do you think you can beat it?");
        }

        // Start the timer
        timer.startTimer();
        // While the user doesn't want to quit, the boss hasn't been defeated and the player hasn't died
        while (input != 'q' && bossDefeated == false && !p1.isDead()) {

            for (int i = 0; i < 11; i++) {
                // If the player has discovered an enemy, follow the fighting process
                if (p1.getxPosition() == enemies[i].getxPosition() && p1.getLevel() == enemies[i].getLevel()) {
                    inBattle = true;
                    System.out.println("\nYou encounter a " + enemies[i].getName() + " on the path...");
                    System.out.println("You have " + playerPotionCount + " potions on hand (+25HP).");
                    // While the player is fighting, Display the stats of player and enemy, and the controls
                    while (inBattle = true) {
                        if (p1.getHealth() <= 0) {
                            System.out.println("\nYou have been defeated in battle.");
                            p1.setDead(true);
                            inBattle = false;
                            break;
                        }
                        if (enemies[i].getHealth() <= 0) {
                            System.out.println("\nThe " + enemies[i].getName() + " has been defeated...");
                            enemies[i].setDead(true);
                            p1.setXp(p1.getXp()+enemies[i].getXp());
                            if (p1.getXp() >= 100) {
                                p1.setXp(p1.getXp()-100);
                                p1.setAttack(p1.getAttack()+4);
                                System.out.println("\nYou leveled up!");
                                System.out.println("\nYour attack has grown more powerful! (+4 attack)");
                            }
                            inBattle = false;
                            break;
                        }
                        System.out.println("\nPlayer: " + p1.getHealth() + "hp\n" + enemies[i].getName() + ": " + enemies[i].getHealth() + "hp\n");
                        System.out.println("You deal " + p1.getAttack() + " damage per attack.");
                        System.out.println(enemies[i].getName() + " will cause " + enemies[i].getAttack() + " damage each turn.\n");
                        System.out.println("Controls:\nAttack:  'w'\nHeal:    's'");
                        input = scan.next().charAt(0);

                        // If the user doesn't enter expected input
                        while (input != 'w' && input != 's') {
                            System.out.println("Please enter one of the options above:");
                            input = scan.next().charAt(0);
                        }

                        switch (input) {
                            // If the player attacks, subtract player and enemy health accordingly
                            case 'w': {
                                enemies[i].setHealth(enemies[i].getHealth() - p1.getAttack());
                                p1.setHealth(p1.getHealth() - enemies[i].getAttack());
                                System.out.println("\n" + p1.getName() + " deals " + p1.getAttack() + " damage.");
                                System.out.println(enemies[i].getName() + " deals " + enemies[i].getAttack() + " damage.");
                                break;
                            }
                            // If the player wants to heal
                            case 's': {
                                // If the player has enough potions, let them heal
                                if (playerPotionCount > 0) {
                                    // Heal to max health dicatated by the players class
                                    if (isWarrior) {
                                        if (p1.getHealth() != 100) {
                                            if (p1.getHealth() >= 80) {
                                                p1.setHealth(100);
                                                System.out.println("\nYou heal to 100hp");
                                            } else {
                                                p1.setHealth(p1.getHealth() + 25);
                                                System.out.println("\nYou heal to " + p1.getHealth() + "hp");
                                            }

                                            playerPotionCount -= 1;
                                            System.out.println("\nYou now have " + playerPotionCount + " potions");
                                            break;
                                        } else {
                                            System.out.println("\nYou are already at max health");
                                        }
                                    }
                                    if (isDwarf) {
                                        if (p1.getHealth() != 125) {
                                            if (p1.getHealth() >= 100) {
                                                p1.setHealth(125);
                                                System.out.println("\nYou heal to 125hp");
                                            } else {
                                                p1.setHealth(p1.getHealth() + 25);
                                                System.out.println("\nYou heal to " + p1.getHealth() + "hp");
                                            }

                                            playerPotionCount -= 1;
                                            System.out.println("\nYou now have " + playerPotionCount + " potions");
                                            break;
                                        } else {
                                            System.out.println("\nYou are already at max health");
                                        }
                                    }
                                    if (isElf) {
                                        if (p1.getHealth() != 75) {
                                            if (p1.getHealth() >= 50) {
                                                p1.setHealth(75);
                                                System.out.println("\nYou heal to 75hp");
                                            } else {
                                                p1.setHealth(p1.getHealth() + 25);
                                                System.out.println("\nYou heal to " + p1.getHealth() + "hp");
                                            }

                                            playerPotionCount -= 1;
                                            System.out.println("\nYou now have " + playerPotionCount + " potions");
                                            break;
                                        } else {
                                            System.out.println("\nYou are already at max health");
                                        }
                                    }
                                    // If the player has no more potions, display no potions message
                                } else {
                                    System.out.println("You don't have anymore potions to use");
                                }

                            }
                        }
                    }
                }
            }

            // Check if the player has stumbled on a max HP potion and heal to max health
            if (isWarrior) {
                p1.checkForPotion(100);
            }
            if (isDwarf) {
                p1.checkForPotion(125);
            }
            if (isElf) {
                p1.checkForPotion(75);
            }

            // If the  boss is dead, set bossDefeated to true
            if (enemies[10].isDead()) {
                bossDefeated = true;
                break;
            }
            // If the player is dead, print a game over message
            if (p1.isDead()) {
                System.out.println("\nYou didn't manage to conquer the enemies of the rpg :(\nBetter luck next time.");
                break;
            }

            // Display the map
            map.printMap();

            // Display the controls
            System.out.println("\nControls:\nMove left:  'a'\nMove right: 'd'\nQuit game:  'q'\n\nChoose a direction: ");
            input = scan.next().charAt(0);

            // If the user inputted incorrect input
            while (input != 'a' && input != 'd' && input != 'q') {
                System.out.println("Please enter one of the controls above:");
                input = scan.next().charAt(0);
            }

            // Update the game depending on the users input
            switch (input) {
                // If the player wants to go left
                case 'a': {
                    // If the player isn't on the boundary of the map 
                    if (!map.playerOnBoundary(p1.getxPosition() - 1, p1.getLevel())) {
                        // If the player is at a tunnel, move them up a level
                        if (map.playerAtTunnel(p1.getxPosition() - 1, p1.getLevel())) {
                            p1.setLevel(p1.getLevel() - 1);
                            map.updateMap(p1.getLevel(), p1.getxPosition(), input, true);
                            // If the player isn't at the boundary or a tunnel, move them left
                        } else {
                            map.updateMap(p1.getLevel(), p1.getxPosition() - 1, input, false);
                            p1.move(input);
                        }
                    }
                    break;
                }
                // If the player wants to go right
                case 'd': {
                    // If the player isn't on the boundary of the map
                    if (!map.playerOnBoundary(p1.getxPosition() + 1, p1.getLevel())) {
                        // If the player is at a tunnel, move them up a level
                        if (map.playerAtTunnel(p1.getxPosition() + 1, p1.getLevel())) {
                            p1.setLevel(p1.getLevel() - 1);
                            map.updateMap(p1.getLevel(), p1.getxPosition(), input, true);
                            // If the player isn't at the boundary or a tunnel, move them right
                        } else {
                            map.updateMap(p1.getLevel(), p1.getxPosition() + 1, input, false);
                            p1.move(input);
                        }
                    }
                    break;
                }
                // If the player wants to quit, display goodbye message
                case 'q': {
                    System.out.println("\nGoodbye!");
                    break;
                }
            }

        }

        // If the player has defeated the boss, display a congratulations message
        if (bossDefeated == true) {
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
            timesFile.writeTime(currentTime);
        }

    }

    /**
     * Class asks the user what character they want to play as and creates a
     * Character
     *
     * @return Returns the players character
     */
    private static Player CreateCharacter() {
        //Initialization for character creation
        int characterSelection = 0;
        String name;
        Player player = null;

        //Asks the user what character they want to play as
        do {
            try{
                System.out.println("Choose your character:");
                System.out.println("1. Warrior\n2. Dwarf\n3. Elf");
                characterSelection = scan.nextInt();

                //Repeats if the user enters incorrect input
                if (characterSelection < 1 || characterSelection > 3) {
                    System.out.println("\nYour number was either too high or too low. Try again.\n");
                }
            } catch(Exception e) {
                System.out.println("\nYour input is invalid, please try again\n");
                scan.reset();
                scan.next();
            }
        } while (characterSelection < 1 || characterSelection > 3);
        

        //Clearing the scanner buffer
        scan.nextLine();

        //Asking for players name and creating chosen character
        switch (characterSelection) {
            //Creates Warrior
            case 1: {
                System.out.println("\nSo you've decided to become a Warrior!\nWhat should your enemies know you by?");
                name = scan.nextLine();
                player = new Warrior(name);
                isWarrior = true;
                break;
            }
            //Creates Dwarf
            case 2: {
                System.out.println("\nSo you've decided to become a Dwarf!\nWhat should your enemies know you by?");
                name = scan.nextLine();
                player = new Dwarf(name);
                isDwarf = true;
                break;
            }
            //Creates Elf
            case 3: {
                System.out.println("\nSo you've decided to become a Elf!\nWhat should your enemies know you by?");
                name = scan.nextLine();
                player = new Elf(name);
                isElf = true;
                break;
            }
            default:
        }

        return player;
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
