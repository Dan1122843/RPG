package rpgGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import rpgCharacters.*;

/**
 * GameSetup class creates the GUI elements for the game
 * @author Liam O'Connor, Daniel Willis, Aidan Lacey ID: 18048495, 19079994,
 * 17994308
 */
public class GameSetup extends DisplayMap {

    //Create attributes for class and main class to use and access
    private final int startPageWidth;
    private final int startPageHeight;
    private final JTextField nameTextBox;
    private final JButton warriorButton;
    private final JButton elfButton;
    private final JButton dwarfButton;
    private final JButton enterButton;
    private final JPanel startPageCharacterButtonsPanel;
    private final JPanel startPageNamePanel;
    private final JLabel objectiveLabel;
    private JLabel nameLabel;
    private final JFrame startPageFrame;
    private final JFrame gameplayFrame;
    private final JPanel statsPanel;
    private final JPanel actionsPanel;
    private final JLabel statsLabel;
    private final JLabel enemyStatsLabel;
    private final JLabel actionLabel;
    private static int character; //used to display the character: warrior = 1, elf = 2, dwarf = 3
    private static Enemy enemy;

    private final JButton left;
    private final JButton right;
    private final JButton attack;
    private final JButton heal;
    private final JButton quit;
    private final JPanel combatPanel;
    private final JPanel mapPanel;

    public int x = 0; //Used for the x movement
    public int y = 425; //Used for y movement
    public boolean demon1Dead = false;
    public boolean demon2Dead = false;
    public boolean spiderDead = false;
    public boolean vampireDead = false;
    public boolean giantDead = false;

    public GameSetup() throws IOException {
        super();
        startPageWidth = 700;
        startPageHeight = 700;

        //Create the components for the starting page
        JLabel welcomeLabel = new JLabel("Welcome to the RPG Game GUI");
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setForeground(Color.white);
        welcomeLabel.setFont(welcomeLabel.getFont().deriveFont(22f));

        BufferedImage mountains = ImageIO.read(new File("./mountains.png"));
        JLabel mountainsImage = new JLabel(new ImageIcon(mountains));
        mountainsImage.setPreferredSize(new Dimension(startPageWidth, (startPageHeight * 45) / 100));
        mountainsImage.setLayout(new BorderLayout());

        mountainsImage.add(welcomeLabel);

        JPanel startPageTopPanel = new JPanel();
        startPageTopPanel.setBackground(Color.black);
        startPageTopPanel.setPreferredSize(new Dimension(startPageWidth, (startPageHeight * 45) / 100));
        startPageTopPanel.add(mountainsImage);

        //Create the components for the character creation
        JLabel characterLabel = new JLabel("Choose your Character: ");
        characterLabel.setForeground(Color.white);

        JButton startButton = new JButton("Start");
        warriorButton = new JButton("Warrior");
        elfButton = new JButton("Elf");
        dwarfButton = new JButton("Dwarf");

        nameLabel = new JLabel("Enter your hero's name: ");
        nameLabel.setForeground(Color.white);
        
        nameTextBox = new JTextField(10);
        enterButton = new JButton("Enter");

        //Create start page panel to add components to
        JPanel startPageStartPanel = new JPanel();
        startPageStartPanel.setBackground(Color.black);
        startPageStartPanel.setPreferredSize(new Dimension(startPageWidth, (startPageHeight * 6) / 100));

        //Create start page character buttons panel to add components to
        startPageCharacterButtonsPanel = new JPanel();
        startPageCharacterButtonsPanel.setBackground(Color.black);
        startPageCharacterButtonsPanel.setVisible(false);
        startPageCharacterButtonsPanel.setPreferredSize(new Dimension(startPageWidth, (startPageHeight * 6) / 100));

        //Create start page name panel to add components to
        startPageNamePanel = new JPanel();
        startPageNamePanel.setBackground(Color.black);
        startPageNamePanel.setVisible(false);
        startPageNamePanel.setPreferredSize(new Dimension(startPageWidth, (startPageHeight * 6) / 100));

        //Add all the components to the panels 
        startPageStartPanel.add(startButton);
        startPageCharacterButtonsPanel.add(characterLabel);
        startPageCharacterButtonsPanel.add(warriorButton);
        startPageCharacterButtonsPanel.add(elfButton);
        startPageCharacterButtonsPanel.add(dwarfButton);
        startPageNamePanel.add(nameLabel);
        startPageNamePanel.add(nameTextBox);
        startPageNamePanel.add(enterButton);

        //Create the instructions panel and label
        JPanel startPageInstructionsPanel = new JPanel();
        startPageInstructionsPanel.setBackground(Color.LIGHT_GRAY);
        startPageInstructionsPanel.setPreferredSize(new Dimension(startPageWidth, (startPageHeight * 45) / 100));

        JLabel instructions = new JLabel("<html><br/><b>How to Play:</b><br/><br/>The aim of the game is to reach the top of the cave "
                + "by defeating the enemies that cross your path.<br/>Reach the other end of each stage to progress to the next level. "
                + "<br/><br/><br/><b>Each enemies health points and damage stats are the "
                + "following:</b><br/><br/>Demon: &ensp;<b>20HP - 2DMG</b><br/>Spider: &emsp;<b>30HP - 4DMG</b><br/>Vampire: "
                + "<b>50HP - 6DMG</b><br/>Giant: &ensp;&emsp;<b>70HP - 8DMG</b><br/>Orc Boss: <b>70HP - 12DMG</b><br/><br/>Have Fun! :)</html>s");
        instructions.setFont(instructions.getFont().deriveFont(14f));

        //Add components to instructions panel
        startPageInstructionsPanel.add(instructions, BorderLayout.NORTH);

        //Create character panel
        JPanel startPageCharacterPanel = new JPanel();
        startPageCharacterPanel.setBackground(Color.LIGHT_GRAY);
        startPageCharacterPanel.setPreferredSize(new Dimension(startPageWidth, (startPageHeight * 45) / 100));

        //Create character subpanels to display class stats
        JPanel WarriorPanel = new JPanel();
        WarriorPanel.setBackground(Color.LIGHT_GRAY);
        WarriorPanel.setPreferredSize(new Dimension((startPageWidth * 32) / 100, 300));
        JLabel WarriorStats = new JLabel("<html><b>Warrior:</b><br/>Health: 100<br/>Attack: 20</html>s");
        WarriorStats.setFont(WarriorStats.getFont().deriveFont(20f));
        BufferedImage warrior = ImageIO.read(new File("./warrior.png"));
        JLabel WarriorImage = new JLabel(new ImageIcon(warrior));
        WarriorPanel.add(WarriorStats, BorderLayout.NORTH);
        WarriorPanel.add(WarriorImage, BorderLayout.SOUTH);

        JPanel ElfPanel = new JPanel();
        ElfPanel.setBackground(Color.LIGHT_GRAY);
        ElfPanel.setPreferredSize(new Dimension((startPageWidth * 33) / 100, 300));
        JLabel ElfStats = new JLabel("<html><b>Elf:</b><br/>Health: 75<br/>Attack: 30</html>s");
        ElfStats.setFont(ElfStats.getFont().deriveFont(20f));
        BufferedImage elf = ImageIO.read(new File("./elf.png"));
        JLabel ElfImage = new JLabel(new ImageIcon(elf));
        ElfPanel.add(ElfStats, BorderLayout.NORTH);
        ElfPanel.add(ElfImage, BorderLayout.SOUTH);

        JPanel DwarfPanel = new JPanel();
        DwarfPanel.setBackground(Color.LIGHT_GRAY);
        DwarfPanel.setPreferredSize(new Dimension((startPageWidth * 32) / 100, 300));
        JLabel DwarfStats = new JLabel("<html><b>Dwarf:</b><br/>Health: 125<br/>Attack: 10</html>s");
        DwarfStats.setFont(DwarfStats.getFont().deriveFont(20f));
        BufferedImage dwarf = ImageIO.read(new File("./dwarf.png"));
        JLabel DwarfImage = new JLabel(new ImageIcon(dwarf));
        DwarfPanel.add(DwarfStats, BorderLayout.NORTH);
        DwarfPanel.add(DwarfImage, BorderLayout.SOUTH);

        //Add the character sub panels to the character panel
        startPageCharacterPanel.add(WarriorPanel, BorderLayout.WEST);
        startPageCharacterPanel.add(ElfPanel, BorderLayout.CENTER);
        startPageCharacterPanel.add(DwarfPanel, BorderLayout.EAST);
        startPageCharacterPanel.setVisible(false);

        //Add the panels to the whole frame panel
        JPanel startPagePanel = new JPanel();
        startPagePanel.setBackground(Color.black);
        startPagePanel.add(startPageTopPanel, BorderLayout.NORTH);
        startPagePanel.add(startPageStartPanel, BorderLayout.CENTER);
        startPagePanel.add(startPageCharacterButtonsPanel, BorderLayout.CENTER);
        startPagePanel.add(startPageNamePanel, BorderLayout.CENTER);
        startPagePanel.add(startPageInstructionsPanel, BorderLayout.SOUTH);
        startPagePanel.add(startPageCharacterPanel, BorderLayout.SOUTH);

        //Create the frame and add starting panel to frame
        startPageFrame = new JFrame();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        startPageFrame.setLocation(dim.width / 2 - startPageWidth / 2, dim.height / 2 - startPageHeight / 2);
        startPageFrame.setPreferredSize(new Dimension(startPageWidth, startPageHeight));
        startPageFrame.setResizable(false);
        startPageFrame.add(startPagePanel);
        startPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startPageFrame.setTitle("Role Player Game GUI");
        startPageFrame.pack();
        startPageFrame.setVisible(true);

        //When the start button is pressed, progress through the GUI
        startButton.addActionListener((ActionEvent e) -> {
            startPageStartPanel.setVisible(false);
            startPageInstructionsPanel.setVisible(false);
            startPageCharacterButtonsPanel.setVisible(true);
            startPageCharacterPanel.setVisible(true);
        });

        int gameplayPageWidth = 800;
        int gameplayPageHeight = 700;

        //Create the gameplay buttons
        left = new JButton("Move Left");
        right = new JButton("Move Right");
        attack = new JButton("Attack");
        heal = new JButton("Heal");
        quit = new JButton("Quit");

        //Create the gameplay panels and their components
        JPanel gameplayTopPanel = new JPanel();
        gameplayTopPanel.setPreferredSize(new Dimension(gameplayPageWidth, (gameplayPageHeight * 11) / 100));
        gameplayTopPanel.setBackground(Color.darkGray);

        objectiveLabel = new JLabel("Find your way out of the dungeon. Defeat all the enemies: ");
        objectiveLabel.setForeground(Color.white);
        gameplayTopPanel.add(objectiveLabel, BorderLayout.CENTER);

        JPanel gameplayCenterPanel = new JPanel();
        gameplayCenterPanel.setPreferredSize(new Dimension(gameplayPageWidth, (gameplayPageHeight * 71) / 100));

        //Create and display the map
        mapPanel = new JPanel();
        mapPanel.setPreferredSize(new Dimension(gameplayPageWidth, (gameplayPageHeight * 71) / 100));
        super.printMap(mapPanel);
        super.setVertMove(425);

        //Create the combat panel and components
        combatPanel = new JPanel();
        combatPanel.setPreferredSize(new Dimension(gameplayPageWidth, (gameplayPageHeight * 71) / 100));
        enemyStatsLabel = new JLabel("Enemy Name:  Enemy Health:  Enemy Attack: ");
        enemyStatsLabel.setFont(enemyStatsLabel.getFont().deriveFont(18f));
        enemyStatsLabel.setBounds(260, 450, 100, 30);
        combatPanel.add(enemyStatsLabel);

        gameplayCenterPanel.add(mapPanel);
        gameplayCenterPanel.add(combatPanel);
        combatPanel.setVisible(false);

        statsPanel = new JPanel();
        actionsPanel = new JPanel();

        //Create the bottom portion panel and it's components
        JPanel gameplayBottomPanel = new JPanel();
        gameplayBottomPanel.setLayout(new BorderLayout());
        gameplayBottomPanel.setPreferredSize(new Dimension(gameplayPageWidth, (gameplayPageHeight * 17) / 100));
        gameplayBottomPanel.setBackground(Color.LIGHT_GRAY);
        actionLabel = new JLabel("Pick an action");
        actionLabel.setForeground(Color.red);

        statsLabel = new JLabel("Player Name:   Health:   Attack:  ");
        statsLabel.setFont(statsLabel.getFont().deriveFont(18f));

        //Add the actions components to the actions panel
        actionsPanel.add(actionLabel);
        actionsPanel.add(left);
        actionsPanel.add(right);
        actionsPanel.add(attack);
        actionsPanel.add(heal);
        actionsPanel.add(quit);
        statsPanel.add(statsLabel);

        //Add the stats and actions panels to the bottom panel
        gameplayBottomPanel.add(statsPanel, BorderLayout.NORTH);
        gameplayBottomPanel.add(actionsPanel);

        //Create the gameplay frame and add the panels
        gameplayFrame = new JFrame();
        gameplayFrame.add(gameplayTopPanel, BorderLayout.NORTH);
        gameplayFrame.add(gameplayCenterPanel, BorderLayout.CENTER);
        gameplayFrame.add(gameplayBottomPanel, BorderLayout.SOUTH);
        gameplayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameplayFrame.setLocation(dim.width / 2 - gameplayPageWidth / 2, dim.height / 2 - gameplayPageHeight / 2);
        gameplayFrame.setTitle("Role Player Game GUI");
        gameplayFrame.setResizable(false);
        gameplayFrame.pack();

        //Set the movement buttons as visible and the attacking ones as false
        left.setVisible(true);
        right.setVisible(true);
        attack.setVisible(false);
        heal.setVisible(false);
        quit.setVisible(false);

        //When the user presses the quit button, terminate the program
        quit.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });

    }

    /**
     * Check the map to see if the player has hit an enemy
     * @param direction String representing the players direction
     * @return Returns an enemy object
     */
    public Enemy checkForEnemy(String direction) {

        enemy = null;

        //If the player is going right, change their x coordinate
        if (direction.equals("right")) {
            x = x + 50;

            //If the players coordinates match that of an enemies, create an enemy object
            if ((getHorisMove() == 150 && getVertMove() == 425) && demon1Dead == false) {
                enemy = new Demon(getHorisMove());
            } else if ((getHorisMove() == 450 && getVertMove() == 425) && demon2Dead == false) {
                enemy = new Demon(getHorisMove());
            } else if ((getHorisMove() == 150 && getVertMove() == 225) && vampireDead == false) {
                enemy = new Vampire(getHorisMove());
            } else if (getHorisMove() == 250 && getVertMove() == 25) {
                enemy = new OrcBoss();
            }

            //If there is an enemy, update the enemy stats label
            if (getEnemy() != null) {
                getEnemyStatsLabel().setText("<html><b>Enemy Name: </b>"
                        + getEnemy().getName() + "<b>&emsp;Health: </b>"
                        + getEnemy().getHealth() + " HP<b>&emsp;Attack: </b>"
                        + getEnemy().getAttack() + " DMG" + "<br/><br/><br/><br/>"
                        + "<br/><br/><br/><br/><br/><br/>&emsp;&emsp;"
                        + "&emsp;&emsp;&emsp;&emsp;<b>You encounter a "
                        + enemy.getName() + "</b></html>");

                //Change the users display from the map display to the combat display
                getLeft().setVisible(false);
                getRight().setVisible(false);

                getAttack().setVisible(true);
                getHeal().setVisible(true);

                getCombatPanel().setVisible(true);

                getMapPanel().setVisible(false);
                getMapPanel().validate();
                getMapPanel().repaint();

            
            } else if (x >= 750) {
                //If the user has reached a tunnel, put them up a level
                if (y == 425 || y == 225) {
                    y = y - 100;
                }
                x = 725;
            }
        }
        //If the player is going left, change their x coordinate
        if (direction.equals("left")) {
            x = x - 50;

            //If the players coordinates match that of an enemies, create an enemy object
            if ((getHorisMove() == 425 && getVertMove() == 325) && spiderDead == false) {
                enemy = new Spider(getHorisMove());
            } else if ((getHorisMove() == 625 && getVertMove() == 125) && giantDead == false) {
                enemy = new Giant(getHorisMove());
            }

            //If there is an enemy, update the enemy stats label
            if (getEnemy() != null) {
                getEnemyStatsLabel().setText("<html><b>Enemy Name: </b>"
                        + getEnemy().getName() + "<b>&emsp;Health: </b>"
                        + getEnemy().getHealth() + " HP<b>&emsp;Attack: </b>"
                        + getEnemy().getAttack() + " DMG" + "<br/><br/><br/><br/>"
                        + "<br/><br/><br/><br/><br/><br/>&emsp;&emsp;"
                        + "&emsp;&emsp;&emsp;&emsp;<b>You encounter a "
                        + enemy.getName() + "</b></html>");

                //Change the users display from the map display to the combat display
                getLeft().setVisible(false);
                getRight().setVisible(false);

                getAttack().setVisible(true);
                getHeal().setVisible(true);

                getCombatPanel().setVisible(true);

                getMapPanel().setVisible(false);
                getMapPanel().validate();
                getMapPanel().repaint();

            } else if (x <= 0) {
                //If the user has reached a tunnel, put them up a level
                if (y == 325 || y == 125) {
                    y = y - 100;
                }
                x = 0;
            }
        }
        //Update the map
        super.setVertMove(y);
        super.setHorisMove(x);
        getMapPanel().validate();
        getMapPanel().repaint();

        return getEnemy();
    }

    /**
     * If the player won a battle
     */
    public void battleWon() {
        //Set the enemy as dead
        if (enemy.getName().equals("Demon") && enemy.getxPosition() == 150) {
            demon1Dead = true;
        } else if (enemy.getName().equals("Demon") && enemy.getxPosition() == 450) {
            demon2Dead = true;
        } else if (enemy.getName().equals("Spider")) {
            spiderDead = true;
        } else if (enemy.getName().equals("Vampire")) {
            vampireDead = true;
        } else if (enemy.getName().equals("Giant")) {
            giantDead = true;
        }

        //Change the users display back to the map panel
        getLeft().setVisible(true);
        getRight().setVisible(true);

        getAttack().setVisible(false);
        getHeal().setVisible(false);

        getCombatPanel().setVisible(false);

        getMapPanel().setVisible(true);
        getMapPanel().validate();
        getMapPanel().repaint();

        //Update the map
        super.setVertMove(y);
        super.setHorisMove(x);
        getMapPanel().validate();
        getMapPanel().repaint();
    }

    //If the player lost, update the display to the game lost screen
    public void battleLost() {
        getStatsLabel().setText("<html><b>You Lost!<br/>You didn't manage to defeat the monsters.</b></html>");
        getActionLabel().setVisible(false);
        getAttack().setVisible(false);
        getHeal().setVisible(false);
        getQuit().setVisible(true);
    }

    //If teh player won the game, update the display to the game won screen
    public void gameWon() {
        getStatsLabel().setText("<html><b>Press Quit to Exit</b></html>");
        getActionLabel().setVisible(false);
        getAttack().setVisible(false);
        getHeal().setVisible(false);
        getQuit().setVisible(true);
    }

    /**
     * @return the nameTextBox
     */
    public JTextField getNameTextBox() {
        return nameTextBox;
    }

    /**
     * @return the warriorButton
     */
    public JButton getWarriorButton() {
        return warriorButton;
    }

    /**
     * @return the elfButton
     */
    public JButton getElfButton() {
        return elfButton;
    }

    /**
     * @return the dwarfButton
     */
    public JButton getDwarfButton() {
        return dwarfButton;
    }

    public static int getCharacter() {
        return character;
    }

    public static void setCharacter(int character) {
        GameSetup.character = character;
    }

    /**
     * @return the enterButton
     */
    public JButton getEnterButton() {
        return enterButton;
    }

    /**
     * @return the startPageCharacterButtonsPanel
     */
    public JPanel getStartPageCharacterButtonsPanel() {
        return startPageCharacterButtonsPanel;
    }

    /**
     * @return the startPageNamePanel
     */
    public JPanel getStartPageNamePanel() {
        return startPageNamePanel;
    }

    /**
     * @return the startPageFrame
     */
    public JFrame getStartPageFrame() {
        return startPageFrame;
    }

    /**
     * @return the gameplayFrame
     */
    public JFrame getGameplayFrame() {
        return gameplayFrame;
    }

    /**
     * @return the objectiveLabel
     */
    public JLabel getObjectiveLabel() {
        return objectiveLabel;
    }

    /**
     * @param nameLabel the nameLabel to set
     */
    public void setNameLabel(JLabel nameLabel) {
        this.nameLabel = nameLabel;
    }

    /**
     * @return the nameLabel
     */
    public JLabel getNameLabel() {
        return nameLabel;
    }

    /**
     * @return the statsLabel
     */
    public JLabel getStatsLabel() {
        return statsLabel;
    }

    /**
     * @return the enemy
     */
    public static Enemy getEnemy() {
        return enemy;
    }

    /**
     * @return the left
     */
    public JButton getLeft() {
        return left;
    }

    /**
     * @return the right
     */
    public JButton getRight() {
        return right;
    }

    /**
     * @return the attack
     */
    public JButton getAttack() {
        return attack;
    }

    /**
     * @return the heal
     */
    public JButton getHeal() {
        return heal;
    }

    /**
     * @return the combatPanel
     */
    public JPanel getCombatPanel() {
        return combatPanel;
    }

    /**
     * @return the mapPanel
     */
    public JPanel getMapPanel() {
        return mapPanel;
    }

    /**
     * @return the enemyStatsLabel
     */
    public JLabel getEnemyStatsLabel() {
        return enemyStatsLabel;
    }

    /**
     * @return the quit
     */
    public JButton getQuit() {
        return quit;
    }

    /**
     * @return the actionLabel
     */
    public JLabel getActionLabel() {
        return actionLabel;
    }
}
