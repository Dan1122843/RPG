package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import rpgCharacters.*;

/**
 *
 * @author liamo
 */
public class GameSetup extends DisplayMap {

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
    private static int character; //used to display the character: warrior = 1, elf = 2, dwarf = 3
    private static Enemy enemy;

    private final JButton left;
    private final JButton right;
    private final JButton attack;
    private final JButton heal;
    private final JPanel combatPanel;
    private final JPanel mapPanel;

    public int x = 0; //Used for the x movement
    public int y = 425; //Used for y movement

    public GameSetup() throws IOException {
        super();
        startPageWidth = 700;
        startPageHeight = 700;

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

        JLabel characterLabel = new JLabel("Choose your Character: ");
        characterLabel.setForeground(Color.white);

        nameTextBox = new JTextField(10);

        enterButton = new JButton("Enter");

        JButton startButton = new JButton("Start");
        warriorButton = new JButton("Warrior");
        elfButton = new JButton("Elf");
        dwarfButton = new JButton("Dwarf");

        nameLabel = new JLabel("Enter your hero's name: ");
        nameLabel.setForeground(Color.white);

        JPanel startPageStartPanel = new JPanel();
        startPageStartPanel.setBackground(Color.black);
        startPageStartPanel.setPreferredSize(new Dimension(startPageWidth, (startPageHeight * 6) / 100));

        startPageCharacterButtonsPanel = new JPanel();
        startPageCharacterButtonsPanel.setBackground(Color.black);
        startPageCharacterButtonsPanel.setVisible(false);
        startPageCharacterButtonsPanel.setPreferredSize(new Dimension(startPageWidth, (startPageHeight * 6) / 100));

        startPageNamePanel = new JPanel();
        startPageNamePanel.setBackground(Color.black);
        startPageNamePanel.setVisible(false);
        startPageNamePanel.setPreferredSize(new Dimension(startPageWidth, (startPageHeight * 6) / 100));

        startPageStartPanel.add(startButton);
        startPageCharacterButtonsPanel.add(characterLabel);
        startPageCharacterButtonsPanel.add(warriorButton);
        startPageCharacterButtonsPanel.add(elfButton);
        startPageCharacterButtonsPanel.add(dwarfButton);
        startPageNamePanel.add(nameLabel);
        startPageNamePanel.add(nameTextBox);
        startPageNamePanel.add(enterButton);

        JPanel startPageInstructionsPanel = new JPanel();
        startPageInstructionsPanel.setBackground(Color.LIGHT_GRAY);
        startPageInstructionsPanel.setPreferredSize(new Dimension(startPageWidth, (startPageHeight * 45) / 100));

        JLabel instructions = new JLabel("<html><br/><b>How to Play:</b><br/><br/>The aim of the game is to reach the top of the cave "
                + "by defeating the enemies that cross your path.<br/>Reach the other end of each stage to progress to the next level. "
                + "<br/><br/><br/><b>Each enemies icon, health points and damage stats are the "
                + "following:</b><br/><br/>Demon: &ensp;<b>D - 20HP - 5DMG</b><br/>Spider: &emsp;<b>S - 30HP - 8DMG</b><br/>Vampire: "
                + "<b>V - 50HP - 15DMG</b><br/>Giant: &ensp;&emsp;<b>G - 70HP - 12DMG</b><br/>Orc Boss: <b>0 - 70HP - 18DMG</b><br/><br/>Have Fun! :)</html>s");
        instructions.setFont(instructions.getFont().deriveFont(14f));

        startPageInstructionsPanel.add(instructions, BorderLayout.NORTH);

        JPanel startPageCharacterPanel = new JPanel();
        startPageCharacterPanel.setBackground(Color.LIGHT_GRAY);
        startPageCharacterPanel.setPreferredSize(new Dimension(startPageWidth, (startPageHeight * 45) / 100));

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

        startPageCharacterPanel.add(WarriorPanel, BorderLayout.WEST);
        startPageCharacterPanel.add(ElfPanel, BorderLayout.CENTER);
        startPageCharacterPanel.add(DwarfPanel, BorderLayout.EAST);
        startPageCharacterPanel.setVisible(false);

        JPanel startPagePanel = new JPanel();
        startPagePanel.setBackground(Color.black);
        startPagePanel.add(startPageTopPanel, BorderLayout.NORTH);
        startPagePanel.add(startPageStartPanel, BorderLayout.CENTER);
        startPagePanel.add(startPageCharacterButtonsPanel, BorderLayout.CENTER);
        startPagePanel.add(startPageNamePanel, BorderLayout.CENTER);
        startPagePanel.add(startPageInstructionsPanel, BorderLayout.SOUTH);
        startPagePanel.add(startPageCharacterPanel, BorderLayout.SOUTH);

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

        startButton.addActionListener((ActionEvent e) -> {
            startPageStartPanel.setVisible(false);
            startPageInstructionsPanel.setVisible(false);
            startPageCharacterButtonsPanel.setVisible(true);
            startPageCharacterPanel.setVisible(true);
        });

        
        warriorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getStartPageCharacterButtonsPanel().setVisible(false);
                getStartPageNamePanel().setVisible(true);
                setCharacter(1);
            }
        });

        elfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getStartPageCharacterButtonsPanel().setVisible(false);
                getStartPageNamePanel().setVisible(true);
                setCharacter(2);
            }
        });

        dwarfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getStartPageCharacterButtonsPanel().setVisible(false);
                getStartPageNamePanel().setVisible(true);
                setCharacter(3);
            }
        });

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startPageFrame.setVisible(false);
                gameplayFrame.setVisible(true);
            }
        });
         
        int gameplayPageWidth = 800;
        int gameplayPageHeight = 700;

        left = new JButton("Move Left");
        right = new JButton("Move Right");

        //Use the following buttons in an attack panel, seperate from the map panel.
        attack = new JButton("Attack");
        heal = new JButton("Heal");

        JPanel gameplayTopPanel = new JPanel();
        gameplayTopPanel.setPreferredSize(new Dimension(gameplayPageWidth, (gameplayPageHeight * 11) / 100));
        gameplayTopPanel.setBackground(Color.darkGray);

        objectiveLabel = new JLabel("Find your way out of the dungeon. Defeat all the enemies: ");
        objectiveLabel.setForeground(Color.white);
        gameplayTopPanel.add(objectiveLabel, BorderLayout.CENTER);

        JPanel gameplayCenterPanel = new JPanel();
        gameplayCenterPanel.setPreferredSize(new Dimension(gameplayPageWidth, (gameplayPageHeight * 71) / 100));

        mapPanel = new JPanel();
        mapPanel.setPreferredSize(new Dimension(gameplayPageWidth, (gameplayPageHeight * 71) / 100));
        super.printMap(mapPanel);
        super.setVertMove(425);

        combatPanel = new JPanel();
        combatPanel.setPreferredSize(new Dimension(gameplayPageWidth, (gameplayPageHeight * 71) / 100));
        enemyStatsLabel = new JLabel("Enemy Name:  Enemy Health:  Enemy Attack: ");
        enemyStatsLabel.setFont(enemyStatsLabel.getFont().deriveFont(18f));
        enemyStatsLabel.setBounds(260, 450, 100, 30);
        combatPanel.add(enemyStatsLabel);

        gameplayCenterPanel.add(mapPanel);
        gameplayCenterPanel.add(combatPanel);
        combatPanel.setVisible(false);

        left.addActionListener((ActionEvent e) -> {
            x = x - 50;
            if (x <= 0) {
                if (y == 325 || y == 125) {
                    y = y - 100;
                }
                x = 0;
            }
            super.setVertMove(y);
            super.setHorisMove(x);
            mapPanel.validate();
            mapPanel.repaint();
        });

        attack.addActionListener((ActionEvent e) -> {

        });

        heal.addActionListener((ActionEvent e) -> {

        });

        statsPanel = new JPanel();
        actionsPanel = new JPanel();

        JPanel gameplayBottomPanel = new JPanel();
        gameplayBottomPanel.setLayout(new BorderLayout());
        gameplayBottomPanel.setPreferredSize(new Dimension(gameplayPageWidth, (gameplayPageHeight * 17) / 100));
        gameplayBottomPanel.setBackground(Color.LIGHT_GRAY);
        JLabel actionLabel = new JLabel("Pick an action");
        actionLabel.setForeground(Color.red);

        statsLabel = new JLabel("Player Name:   Health:   Attack:  ");
        statsLabel.setFont(statsLabel.getFont().deriveFont(18f));

        actionsPanel.add(actionLabel);
        actionsPanel.add(left);
        actionsPanel.add(right);
        actionsPanel.add(attack);
        actionsPanel.add(heal);
        statsPanel.add(statsLabel);

        gameplayBottomPanel.add(statsPanel, BorderLayout.NORTH);
        gameplayBottomPanel.add(actionsPanel);

        gameplayFrame = new JFrame();
        gameplayFrame.add(gameplayTopPanel, BorderLayout.NORTH);
        gameplayFrame.add(gameplayCenterPanel, BorderLayout.CENTER);
        gameplayFrame.add(gameplayBottomPanel, BorderLayout.SOUTH);
        gameplayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameplayFrame.setLocation(dim.width / 2 - gameplayPageWidth / 2, dim.height / 2 - gameplayPageHeight / 2);
        gameplayFrame.setTitle("Role Player Game GUI");
        gameplayFrame.pack();

        left.setVisible(true);
        right.setVisible(true);
        attack.setVisible(false);
        heal.setVisible(false);
    }

    public Enemy checkForEnemy() {

        enemy = null;
        x = x + 50;

        if ((getHorisMove() == 150 || getHorisMove() == 450) && getVertMove() == 425) {
            enemy = new Demon(getHorisMove());
        } else if (getHorisMove() == 250 && getVertMove() == 325) {
            enemy = new Spider(getHorisMove());
        } else if (getHorisMove() == 150 && getVertMove() == 225) {
            enemy = new Vampire(getHorisMove());
        } else if (getHorisMove() == 450 && getVertMove() == 125) {
            enemy = new Giant(getHorisMove());
        } else if (getHorisMove() == 250 && getVertMove() == 25) {
            enemy = new OrcBoss();
        }

        /*g.drawImage(spider, 350, 350, c);
        g.drawImage(vampire, 250, 250, c);
        g.drawImage(giant, 550, 150, c);
        g.drawImage(orcBoss, 350, 50, c);*/
        if (getEnemy() != null) {
            enemyStatsLabel.setText("<html><b>Enemy Name: </b>" + getEnemy().getName()
                    + "<b>&emsp;Health: </b>" + getEnemy().getHealth()
                    + " HP<b>&emsp;Attack: </b>" + getEnemy().getAttack() + " DMG");

            getLeft().setVisible(false);
            getRight().setVisible(false);

            getAttack().setVisible(true);
            getHeal().setVisible(true);

            getCombatPanel().setVisible(true);

            getMapPanel().setVisible(false);
            getMapPanel().validate();
            getMapPanel().repaint();

        } else if (x >= 750) {

            if (y == 425 || y == 225) {
                y = y - 100;
            }
            x = 725;
        }
        super.setVertMove(y);
        super.setHorisMove(x);
        getMapPanel().validate();
        getMapPanel().repaint();

        return getEnemy();
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
}
