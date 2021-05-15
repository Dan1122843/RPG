package rpgCharacters;

/**
 *
 * @author Liam O'Connor, Daniel Willis, Aidan Lacey ID: 18048495, 19079994,
 * 17994308
 */
public class Player extends Character {

    public Player(String name, int health, int attack) {
        super(name, health, attack, 0, false, 1, 5);
    }

    /**
     * The move method moves the players x position depending on user input
     *
     * @param movement The input taken from the user deciding which direction to
     * go
     */
    public void move(char movement) {

        switch (movement) {
            case 'a':
                this.setxPosition(this.getxPosition() - 1);
                break;
            case 'd':
                this.setxPosition(this.getxPosition() + 1);
                break;
            default:
                System.out.println("You can only move left and right using 'a' & 'd'");
        }
    }

    /**
     * Checks if the players location matches that of a max HP potion and if it is, replenish health
     * @param maxHP The players max health dependent on their class type
     */
    public void checkForPotion(int maxHP) {

        // If the players location matches that of a potion, replenish to full HP, and display found potion message to user
        if ((super.getLevel() == 3 && super.getxPosition() == 1) || (super.getLevel() == 2 && super.getxPosition() == 18) || (super.getLevel() == 2 && super.getxPosition() == 1)) {
            super.setHealth(maxHP);
            System.out.println("\nYou stumble upon a max HP potion. It must be your lucky day.");
        }
    }

}
