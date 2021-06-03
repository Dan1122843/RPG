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

    private JFrame gameplayFrame;
    private static int character; //used to display the character: warrior = 1, elf = 2, dwarf = 3

    public static int getCharacter() {
        return character;
    }

    public static void setCharacter(int character) {
        GameSetup.character = character;
    }

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

        JTextField nameTextBox = new JTextField(10);

        JButton enterButton = new JButton("Enter");

        JButton startButton = new JButton("Start");
        JButton warriorButton = new JButton("Warrior");
        JButton elfButton = new JButton("Elf");
        JButton dwarfButton = new JButton("Dwarf");

        JLabel nameLabel = new JLabel("Enter your hero's name: ");
        nameLabel.setForeground(Color.white);

        JPanel startPageStartPanel = new JPanel();
        startPageStartPanel.setBackground(Color.black);
        startPageStartPanel.setPreferredSize(new Dimension(startPageWidth, (startPageHeight * 6) / 100));

        JPanel startPageCharacterButtonsPanel = new JPanel();
        startPageCharacterButtonsPanel.setBackground(Color.black);
        startPageCharacterButtonsPanel.setVisible(false);
        startPageCharacterButtonsPanel.setPreferredSize(new Dimension(startPageWidth, (startPageHeight * 6) / 100));

        JPanel startPageNamePanel = new JPanel();
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

        JFrame startPageFrame = new JFrame();
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
        
        Player player = null;
        
        warriorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startPageCharacterButtonsPanel.setVisible(false);
                startPageNamePanel.setVisible(true);
                setCharacter(1);
                /*player = new Warrior("");*/
            }
        });

        elfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startPageCharacterButtonsPanel.setVisible(false);
                startPageNamePanel.setVisible(true);
                setCharacter(2);
                /*player = new Elf("");*/
            }
        });

        dwarfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startPageCharacterButtonsPanel.setVisible(false);
                startPageNamePanel.setVisible(true);
                setCharacter(3);
                /*player = new Dwarf("");*/
            }
        });

        enterButton.addActionListener((ActionEvent e) -> {
            startPageFrame.setVisible(false);
            gameplayFrame.setVisible(true);
        });

        int gameplayPageWidth = 800;
        int gameplayPageHeight = 700;
        
        JButton left = new JButton("Move Left");
        JButton right = new JButton("Move Right");

        //Use the following buttons in an attack panel, seperate from the map panel.
        JButton attack = new JButton("Attack");
        JButton heal = new JButton("Heal");

        JPanel gameplayTopPanel = new JPanel();
        gameplayTopPanel.setPreferredSize(new Dimension(gameplayPageWidth, (gameplayPageHeight * 11) / 100));
        gameplayTopPanel.setBackground(Color.darkGray);

        JLabel objectiveLabel = new JLabel("Find your way out of the dungeon. Defeat all the enemies: ");
        objectiveLabel.setForeground(Color.white);
        gameplayTopPanel.add(objectiveLabel, BorderLayout.CENTER);

        JPanel gameplayCenterPanel = new JPanel();
        gameplayCenterPanel.setPreferredSize(new Dimension(gameplayPageWidth, (gameplayPageHeight * 71) / 100));
        super.printMap(gameplayCenterPanel);
        super.setVertMove(425);
        
        JPanel combatPanel = new JPanel(null);
        gameplayCenterPanel.setPreferredSize(new Dimension(gameplayPageWidth, (gameplayPageHeight * 71) / 100));
        JLabel playerHealthLabel = new JLabel("Health: ");
        JLabel playerAttackLabel = new JLabel("Attack: ");
        JLabel playerPotionLabel = new JLabel("Potions: ");
        JLabel enemyHealthLabel = new JLabel("Health: ");
        JLabel enemyAttackLabel = new JLabel("Attack: ");
        combatPanel.add(playerHealthLabel);
        combatPanel.add(playerAttackLabel);
        combatPanel.add(playerPotionLabel);
        combatPanel.add(enemyHealthLabel);
        combatPanel.add(enemyAttackLabel);
        playerHealthLabel.setBounds(260, 450, 100, 30);
        playerAttackLabel.setBounds(370, 450, 100, 30);
        playerPotionLabel.setBounds(480, 450, 100, 30);
        enemyHealthLabel.setBounds(320, 190, 100, 30);
        enemyAttackLabel.setBounds(420, 190, 100, 30);

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
            gameplayCenterPanel.validate();
            gameplayCenterPanel.repaint();
        });

        right.addActionListener((ActionEvent e) -> {
            x = x + 50;

            if (getHorisMove() == 100 && getVertMove() == 425){
            
            left.setEnabled(false);
            right.setEnabled(false);
            left.setVisible(false);
            right.setVisible(false);
            
            attack.setEnabled(true);
            heal.setEnabled(true);
            attack.setVisible(true);
            heal.setVisible(true);
            
            gameplayFrame.add(combatPanel);
            
            gameplayCenterPanel.setVisible(false);            
            gameplayCenterPanel.validate();
            gameplayCenterPanel.repaint();
            }
            else if (x >= 750) {

                if (y == 425 || y == 225) {
                    y = y - 100;
                }
                x = 725;
            }
            super.setVertMove(y);
            super.setHorisMove(x);
            gameplayCenterPanel.validate();
            gameplayCenterPanel.repaint();
        });
        
        attack.addActionListener ((ActionEvent e) -> {
            
        });
        
        heal.addActionListener ((ActionEvent e) -> {
            
        });

        JPanel gameplayBottomPanel = new JPanel();
        gameplayBottomPanel.setPreferredSize(new Dimension(gameplayPageWidth, (gameplayPageHeight * 17) / 100));
        gameplayBottomPanel.setBackground(Color.LIGHT_GRAY);
        gameplayBottomPanel.setLayout(new FlowLayout());
        JLabel actionLabel = new JLabel("Pick an action");
        actionLabel.setForeground(Color.red);
        gameplayBottomPanel.add(actionLabel, BorderLayout.LINE_START);
        gameplayBottomPanel.add(left, BorderLayout.WEST);
        gameplayBottomPanel.add(right, BorderLayout.EAST);
        gameplayBottomPanel.add(attack, BorderLayout.NORTH);
        gameplayBottomPanel.add(heal, BorderLayout.SOUTH);

        gameplayFrame = new JFrame();
        gameplayFrame.add(gameplayTopPanel, BorderLayout.NORTH);
        gameplayFrame.add(gameplayCenterPanel, BorderLayout.CENTER);
        gameplayFrame.add(gameplayBottomPanel, BorderLayout.SOUTH);
        gameplayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameplayFrame.setLocation(dim.width / 2 - gameplayPageWidth / 2, dim.height / 2 - gameplayPageHeight / 2);
        gameplayFrame.setTitle("Role Player Game GUI");
        gameplayFrame.pack();
        
        left.setEnabled(true);
        right.setEnabled(true);
        left.setVisible(true);
        right.setVisible(true);
        attack.setEnabled(false);
        heal.setEnabled(false);
        attack.setVisible(false);
        heal.setVisible(false);

    }
}
