package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author liamo
 */
public class GameSetup {

    private final int startPageWidth;
    private final int startPageHeight;

    private JFrame gameplayFrame;

    public GameSetup() throws IOException {
        startPageWidth = 700;
        startPageHeight = 670;

        JButton b1 = new JButton("Warrior");
        JButton b2 = new JButton("Elf");
        JButton b3 = new JButton("Dwarf");

        b1.addActionListener((ActionEvent e) -> {
            gameplayFrame.setVisible(true);
        });

        b2.addActionListener((ActionEvent e) -> {
            gameplayFrame.setVisible(true);
        });

        b3.addActionListener((ActionEvent e) -> {
            gameplayFrame.setVisible(true);
        });

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

        JPanel startPageCenterPanel = new JPanel();
        startPageCenterPanel.setBackground(Color.black);
        startPageCenterPanel.add(characterLabel);
        startPageCenterPanel.add(b1);
        startPageCenterPanel.add(b2);
        startPageCenterPanel.add(b3);

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

        gameplayFrame = new JFrame();

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
        gameplayFrame.setVisible(false);

    }
}
