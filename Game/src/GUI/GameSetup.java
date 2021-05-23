package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author liamo
 */

public class GameSetup extends DisplayMap implements ActionListener {
    
    private JFrame frame1;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel1_5;
    
    private Image img;
    
    private JFrame frame2;
    private JPanel panel4;
    private JPanel panel5;
    private JPanel panel6;

    private JLabel label3;
    private JLabel label4;
    
        
    
    public GameSetup() throws IOException{
        
        frame1 = new JFrame();
        
public class GameSetup {

    private final int startPageWidth;
    private final int startPageHeight;

    private JFrame gameplayFrame;

    public GameSetup() throws IOException {
        startPageWidth = 700;
        startPageHeight = 670;

        JLabel welcomeLabel = new JLabel("Welcome to the RPG Game GUI");
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setForeground(Color.white);
        welcomeLabel.setFont(welcomeLabel.getFont().deriveFont(22f));

        BufferedImage mountains = ImageIO.read(new File("./mountains.png"));
        JLabel mountainsImage = new JLabel(new ImageIcon(mountains));
        mountainsImage.setPreferredSize(new Dimension(startPageWidth, 300));
        mountainsImage.setLayout(new BorderLayout());

        mountainsImage.add(welcomeLabel);

        JPanel startPageTopPanel = new JPanel();
        startPageTopPanel.setBackground(Color.GRAY);
        startPageTopPanel.setPreferredSize(new Dimension(startPageWidth, 300));
        startPageTopPanel.add(mountainsImage);

        JLabel characterLabel = new JLabel("Choose your Character: ");
        characterLabel.setForeground(Color.white);

        JTextField nameTextBox = new JTextField(10);
        nameTextBox.setVisible(false);

        JButton enterButton = new JButton("Enter");
        enterButton.setVisible(false);

        JButton warriorButton = new JButton("Warrior");
        JButton elfButton = new JButton("Elf");
        JButton dwarfButton = new JButton("Dwarf");

        JLabel nameLabel = new JLabel("Enter your hero's name: ");
        nameLabel.setForeground(Color.white);
        nameLabel.setVisible(false);

        
        
        JPanel startPageCenterPanel = new JPanel();
        startPageCenterPanel.setBackground(Color.black);
        startPageCenterPanel.add(characterLabel);
        startPageCenterPanel.add(warriorButton);
        startPageCenterPanel.add(elfButton);
        startPageCenterPanel.add(dwarfButton);
        startPageCenterPanel.add(nameLabel);
        startPageCenterPanel.add(nameTextBox);
        startPageCenterPanel.add(enterButton);

        JPanel startPageBottomPanel = new JPanel();
        startPageBottomPanel.setBackground(Color.LIGHT_GRAY);
        startPageBottomPanel.setPreferredSize(new Dimension(startPageWidth, 300));

        JPanel WarriorPanel = new JPanel();
        WarriorPanel.setBackground(Color.LIGHT_GRAY);
        WarriorPanel.setPreferredSize(new Dimension(225, 300));
        JLabel WarriorStats = new JLabel("<html><b>Warrior:</b><br/>Health: 100<br/>Attack: 20</html>s");
        WarriorStats.setFont(WarriorStats.getFont().deriveFont(20f));
        BufferedImage warrior = ImageIO.read(new File("./warrior.png"));
        JLabel WarriorImage = new JLabel(new ImageIcon(warrior));
        WarriorPanel.add(WarriorStats, BorderLayout.NORTH);
        WarriorPanel.add(WarriorImage, BorderLayout.SOUTH);

        JPanel ElfPanel = new JPanel();
        ElfPanel.setBackground(Color.LIGHT_GRAY);
        ElfPanel.setPreferredSize(new Dimension(225, 300));
        JLabel ElfStats = new JLabel("<html><b>Elf:</b><br/>Health: 75<br/>Attack: 30</html>s");
        ElfStats.setFont(ElfStats.getFont().deriveFont(20f));
        BufferedImage elf = ImageIO.read(new File("./elf.png"));
        JLabel ElfImage = new JLabel(new ImageIcon(elf));
        ElfPanel.add(ElfStats, BorderLayout.NORTH);
        ElfPanel.add(ElfImage, BorderLayout.SOUTH);

        JPanel DwarfPanel = new JPanel();
        DwarfPanel.setBackground(Color.LIGHT_GRAY);
        DwarfPanel.setPreferredSize(new Dimension(225, 300));
        JLabel DwarfStats = new JLabel("<html><b>Dwarf:</b><br/>Health: 125<br/>Attack: 10</html>s");
        DwarfStats.setFont(DwarfStats.getFont().deriveFont(20f));
        BufferedImage dwarf = ImageIO.read(new File("./dwarf.png"));
        JLabel DwarfImage = new JLabel(new ImageIcon(dwarf));
        DwarfPanel.add(DwarfStats, BorderLayout.NORTH);
        DwarfPanel.add(DwarfImage, BorderLayout.SOUTH);

        startPageBottomPanel.add(WarriorPanel, BorderLayout.WEST);
        startPageBottomPanel.add(ElfPanel, BorderLayout.CENTER);
        startPageBottomPanel.add(DwarfPanel, BorderLayout.EAST);

        JFrame startPageFrame = new JFrame();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        startPageFrame.setLocation(dim.width / 2 - startPageWidth / 2, dim.height / 2 - startPageHeight / 2);
        startPageFrame.setPreferredSize(new Dimension(startPageWidth, startPageHeight));
        startPageFrame.setResizable(false);
        startPageFrame.add(startPageTopPanel, BorderLayout.NORTH);
        startPageFrame.add(startPageCenterPanel, BorderLayout.CENTER);
        startPageFrame.add(startPageBottomPanel, BorderLayout.SOUTH);
        startPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startPageFrame.setTitle("Role Player Game GUI");
        startPageFrame.pack();
        startPageFrame.setVisible(true);
        
        warriorButton.addActionListener((ActionEvent e) -> {
            characterLabel.setVisible(false);
            warriorButton.setVisible(false);
            elfButton.setVisible(false);
            dwarfButton.setVisible(false);
            nameLabel.setVisible(true);
            nameTextBox.setVisible(true);
            enterButton.setVisible(true);
        });
        
        elfButton.addActionListener((ActionEvent e) -> {
            characterLabel.setVisible(false);
            warriorButton.setVisible(false);
            elfButton.setVisible(false);
            dwarfButton.setVisible(false);
            nameLabel.setVisible(true);
            nameTextBox.setVisible(true);
            enterButton.setVisible(true);
        });
        
        dwarfButton.addActionListener((ActionEvent e) -> {
            characterLabel.setVisible(false);
            warriorButton.setVisible(false);
            elfButton.setVisible(false);
            dwarfButton.setVisible(false);
            nameLabel.setVisible(true);
            nameTextBox.setVisible(true);
            enterButton.setVisible(true);
        });


        panel1_5 = new JPanel();
        panel1_5.setBackground(Color.black);
        panel1_5.setPreferredSize(new Dimension(600, 40));
        
        panel2 = new JPanel();
        JLabel label2 = new JLabel("Choose your Character: ");
        
        panel2.setBackground(Color.LIGHT_GRAY);
        panel2.setPreferredSize(new Dimension(600, 300));
        panel2.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));
        panel2.add(label2);
        panel2.add(b1);
        panel2.add(b2);
        panel2.add(b3);
        
        frame1 = new JFrame();
        frame1.add(panel1, BorderLayout.NORTH);
        frame1.add(panel1_5, BorderLayout.CENTER);
        frame1.add(panel2, BorderLayout.SOUTH);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setTitle("Role Player Game GUI");
        frame1.pack();
        frame1.setVisible(true);    
        
    
        frame2 = new JFrame();
        
        JButton left = new JButton("a");
        JButton right = new JButton("f");
        JButton attack = new JButton("w");
        JButton heal = new JButton("s");
        
        left.addActionListener(this);
        
        panel4 = new JPanel();
        panel4.setPreferredSize(new Dimension(800, 80));
        panel4.setBackground(Color.darkGray);
        
        label3 = new JLabel("Find your way out of the dungeon. Defeat all the enemies: ");
        label3.setForeground(Color.white);
        panel4.add(label3, BorderLayout.CENTER);
        
        panel5 = new JPanel();
        panel5.setBackground(Color.white);
        panel5.setPreferredSize(new Dimension(800, 500));
        super.printMap(panel5);
        
        panel6 = new JPanel();
        panel6.setPreferredSize(new Dimension(800, 120));
        panel6.setBackground(Color.LIGHT_GRAY);
        panel6.setLayout(new FlowLayout());
        label4 = new JLabel("Pick an action");
        label4.setForeground(Color.red);
        panel6.add(label4, BorderLayout.LINE_START);
        panel6.add(left, BorderLayout.WEST);
        panel6.add(right, BorderLayout.EAST);
        panel6.add(attack, BorderLayout.NORTH);
        panel6.add(heal, BorderLayout.SOUTH);
        
        frame2 = new JFrame();
        frame2.add(panel4, BorderLayout.NORTH);
        frame2.add(panel5, BorderLayout.CENTER);
        frame2.add(panel6, BorderLayout.SOUTH);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setTitle("Role Player Game GUI");
        frame2.pack();
        frame2.setVisible(false);
        
        
    }
    public void printToPanel5(){
        
    }
        enterButton.addActionListener((ActionEvent e) -> {
            startPageFrame.setVisible(false);
            gameplayFrame.setVisible(true);
        });

        JButton left = new JButton("Move Left");
        JButton right = new JButton("Move Right");
        JButton attack = new JButton("Attack");
        JButton heal = new JButton("Heal");

        left.addActionListener((ActionEvent e) -> {
            //Add action
        });

        JPanel gameplayTopPanel = new JPanel();
        gameplayTopPanel.setPreferredSize(new Dimension(800, 80));
        gameplayTopPanel.setBackground(Color.darkGray);

        JLabel objectiveLabel = new JLabel("Find your way out of the dungeon. Defeat all the enemies: ");
        objectiveLabel.setForeground(Color.white);
        gameplayTopPanel.add(objectiveLabel, BorderLayout.CENTER);

        JPanel gameplayCenterPanel = new JPanel();
        gameplayCenterPanel.setBackground(Color.white);
        gameplayCenterPanel.setPreferredSize(new Dimension(800, 800));

        JPanel gameplayBottomPanel = new JPanel();
        gameplayBottomPanel.setPreferredSize(new Dimension(800, 120));
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
        gameplayFrame.setTitle("Role Player Game GUI");
        gameplayFrame.pack();

    }
    
    public JPanel getGameSetup(){
        return this.panel5;
    }
}
