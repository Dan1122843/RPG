package rpgCharacters;

/**
 *
 * @author Liam O'Connor, Daniel Willis, Aidan Lacey ID: 18048495, 19079994,
 * 17994308
 */
public abstract class Character {

    private String name;
    private int health;
    private int attack;
    private int xp;
    private boolean isDead;
    private int xPosition;
    private int level;

    /**
     * Character constructor which creates a player with a name, health, attack
     *
     * @param name Players name as a string
     * @param health Players health as an int
     * @param attack Players attack stat as an int
     * @param xp
     * @param isDead
     * @param xPosition
     * @param level
     */
    public Character(String name, int health, int attack, int xp, boolean isDead, int xPosition, int level) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.xp = xp;
        this.isDead = isDead;
        this.xPosition = xPosition;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        this.isDead = dead;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public boolean isIsDead() {
        return isDead;
    }

    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
