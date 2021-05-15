package rpgCharacters;

import java.util.Random;

/**
 *
 * @author Liam O'Connor, Daniel Willis, Aidan Lacey ID: 18048495, 19079994,
 * 17994308
 */
public class Enemy extends Character {

    public Enemy(String name, int health, int attack, int xp, int xPosition, int level) {
        super(name, health, attack, xp, false, xPosition, level);
        
    }
    
    public void newXPosition(){
        Random rand = new Random();
        int xPosition = rand.nextInt((16) + 2);
        if(super.getLevel() == 3 && super.getxPosition() == 2 || super.getLevel() == 2 && super.getxPosition() == 17){
            super.setxPosition(xPosition);
        }
    }

}