package rpgMap;

/**
 *
 * @author Liam O'Connor, Daniel Willis, Aidan Lacey ID: 18048495, 19079994,
 * 17994308
 */
public class Map {

    // Generate attributes
    int map_height = 7;
    int map_width = 20;
    int levels = map_height - 2;
    public char[][] map = new char[map_height][map_width];
    public int i, j;
    public int potionLevel, potionXPos;

    /**
     * Generate the map layout
     */
    public void generateMapLayout() {
        for (i = 1; i < map_width - 1; i++) {
            map[0][i] = '~';
            map[map_height - 1][i] = '_';
        }

        for (i = 1; i < map_height - 1; i++) {
            if (i == levels) {
                map[i][0] = '|';
                map[i][map_width - 1] = 'O';
            } else if (i == map_height - (map_height - 1)) {
                map[i][0] = 'O';
                map[i][map_width - 1] = '|';
            } else {
                map[i][0] = 'O';
                map[i][map_width - 1] = 'O';
            }

        }
        for (i = 1; i < map_height - 1; i++) {
            for (j = 1; j < map_width - 1; j++) {
                map[i][j] = ' ';
            }
        }
    }

    /**
     * Print the map to the user
     */
    public void printMap() {
        for (i = 0; i < map_height; i++) {
            for (j = 0; j < map_width; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Update the map to display the players current location
     *
     * @param currentLevel The players current level
     * @param newX The players new X location
     * @param movement The players move direction
     * @param movingLevels Boolean for if the player is moving levels
     */
    public void updateMap(int currentLevel, int newX, char movement, boolean movingLevels) {
        // If the player isn't moving levels, move them on the X axis
        if (!movingLevels) {
            switch (movement) {
                case 'a':
                    map[currentLevel][newX + 1] = ' ';
                    break;
                case 'd':
                    map[currentLevel][newX - 1] = ' ';
                    break;
            }

            // If the player is moving levels, put them up one level
        } else {
            map[currentLevel + 1][newX] = ' ';

        }

        //Set the new location as X on the map
        putPlayerOnMap(currentLevel, newX);
    }

    /**
     * Put the player on the map
     *
     * @param level Players level
     * @param xCoord Players x coordinate
     */
    public void putPlayerOnMap(int level, int xCoord) {
        map[level][xCoord] = 'X';
    }

    /**
     * Places maxHP potions on the map for players to pick up
     */
    public void putPotionsOnMap() {
        potionLevel = 3;
        potionXPos = 1;
        map[potionLevel][potionXPos] = 'P';
        potionLevel = 2;
        potionXPos = 18;
        map[potionLevel][potionXPos] = 'P';
        potionLevel = 2;
        potionXPos = 1;
        map[potionLevel][potionXPos] = 'P';
    }

    // Get how many levels there are on the map
    public int getLevels() {
        return levels;
    }

    /**
     * Display the enemies on the map
     *
     * @param level Enemies level
     * @param xPosition Enemies x position
     * @param enemy What type of enemy
     */
    public void putEnemiesOnMap(int level, int xPosition, String enemy) {
        switch (enemy) {
            case "Demon":
                map[level][xPosition] = 'D';
                break;
            case "Spider":
                map[level][xPosition] = 'S';
                break;
            case "Vampire":
                map[level][xPosition] = 'V';
                break;
            case "Giant":
                map[level][xPosition] = 'G';
                break;
            case "OrcBoss":
                map[level][xPosition] = '0';
                break;
        }
    }

    /**
     * Method checks if the player is on the boundary
     *
     * @param xPosition Players x position
     * @param level Players level
     * @return Returns true if the player is on the boundary
     */
    public boolean playerOnBoundary(int xPosition, int level) {
        // If the player is on the boundary, display boundary message and return false
        if (map[level][xPosition] == '|') {
            System.out.println("\nYou can't go that way. The only way out is up.\n");
            return true;
        }
        return false;
    }

    /**
     * Method checks if the player is at a tunnel, if they are, put them up a
     * level
     *
     * @param xPosition Players x position
     * @param level Players current level
     * @return Returns if the player is at a tunnel
     */
    public boolean playerAtTunnel(int xPosition, int level) {
        // If the player is at a tunnel
        if (map[level][xPosition] == 'O') {
            // Display increase level message
            System.out.println("\nYou've reached the end of level " + level + "."
                    + " You find yourself on a new level.\nAs you enter, the path behind you collapses.\n");
            // If the player is on the top level, display final level message.
            if (level - 1 == 1) {
                System.out.println("You have reached the top level. Beware, a powerful enemy is lurking nearby. Get ready.\n");
            }
            // Collapse the tunnel that the player has travelled through
            map[level][xPosition] = '|';
            map[level - 1][xPosition] = '|';
            return true;
        }
        return false;
    }

}
