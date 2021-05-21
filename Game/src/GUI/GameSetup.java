/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;


import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;


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
        
        
        JButton b1 = new JButton("Warrior");
        JButton b2 = new JButton("Elf");
        JButton b3 = new JButton("Dwarf");
        
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                frame2.setVisible(true);
            }
        });
        
        b2.addActionListener(this);
        b3.addActionListener(this);
        
        JLabel label1 = new JLabel("Welcome to RPGame GUI.");
        
        //myPicture = ImageIO.read(new File("src/mountains.png"));
        //JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        //picLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 300, 600));
        
        
        panel1 = new JPanel();
        panel1.setBackground(Color.GRAY);
        panel1.setPreferredSize(new Dimension(600, 300));
        panel1.setBorder(BorderFactory.createEmptyBorder(100, 200, 100, 100));
        panel1.setLayout(new BorderLayout());
        panel1.add(label1);
        //panel1.add(picLabel);
        

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

    public void addImg() {
        try {
            img = ImageIO.read(new File("src/mountains.png"));
        } catch (IOException ex){}
    }
    
    public void paintComponent(Graphics g) {
        this.paintComponent(g);
        g.drawImage(img, 0, 0, this.panel1);

    }
    
    public JPanel getGameSetup(){
        return this.panel5;
    }
}
