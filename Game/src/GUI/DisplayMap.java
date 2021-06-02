/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import rpgCharacters.*;

/**
 *
 * @author Liam O'Connor
 */
public class DisplayMap implements ActionListener{
    
    private JPanel gameplayCenterPanel; 
    private int horisMove;
    private int vertMove;

    public int getHorisMove() {
        return horisMove;
    }

    public void setHorisMove(int horisMove) {
        this.horisMove = horisMove;
    }
    
    public int getVertMove() {
        return vertMove;
    }
    
    public void setVertMove(int vertMove) {
        this.vertMove = vertMove;
    }    
    
    public DisplayMap() throws IOException{
              
        this.gameplayCenterPanel = new JPanel();
        printMap(this.gameplayCenterPanel);

    }
    
    public void printMap(JPanel gameplayCenterPanel) throws IOException{
        gameplayCenterPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        gameplayCenterPanel.setBackground(new Color(10, 20, 50));
        gameplayCenterPanel.add(new DrawCharacter());
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    class DrawCharacter extends JPanel{

        BufferedImage warrior;
        BufferedImage elf;
        BufferedImage dwarf;
        
        BufferedImage demon;
        BufferedImage spider;
        BufferedImage vampire;
        BufferedImage giant;
        BufferedImage orcBoss;
        
        /*
        Dimentions of the BufferedImages(px):
        deamon: 50w, 46h
        spider: 50w, 34h
        vampire: 50w, 25h
        giant:50w, 52h
        orcboss: 50w, 37h
        */
        
        DrawCharacter() throws IOException {
            this.warrior = ImageIO.read(new File("./warrior.png"));
            this.elf = ImageIO.read(new File("./elf.png"));
            this.dwarf = ImageIO.read(new File("./dwarf.png"));
            this.setPreferredSize(new Dimension(50, 50));
            
            this.demon = ImageIO.read(new File("./demon.jpg"));
            this.spider = ImageIO.read(new File("./spider.jpg"));
            this.vampire = ImageIO.read(new File("./vampire.jpg"));
            this.giant = ImageIO.read(new File("./giant.jpg"));
            this.orcBoss = ImageIO.read(new File("./orcboss.jpg"));
            
            
        }
	
	public Dimension getPreferredSize() {
		return new Dimension(800, 500);
	}
        	
	public void paintComponent(Graphics g) {
            
            Component c = getGameplayCenterPanel();
            paintBorder(c, g, 0, 0, 800, 500);
            if(GameSetup.getCharacter() == 1) 
                g.drawImage(warrior, getHorisMove(), getVertMove(), 75, 75, c);
            if(GameSetup.getCharacter() == 2) 
                g.drawImage(elf, getHorisMove(), getVertMove(), 75, 75, c);
            if(GameSetup.getCharacter() == 3) 
                g.drawImage(dwarf, getHorisMove(), getVertMove(), 75, 75, c);
            
            
            //g.drawImage("", xdist, ydist, c);
            g.drawImage(demon, 250, 450, c);
            g.drawImage(demon, 550, 450, c);
            g.drawImage(spider, 350, 350, c);
            g.drawImage(vampire, 350, 250, c);
            g.drawImage(giant, 350, 150, c);
            g.drawImage(orcBoss, 350, 50, c);
                         
	}
               
        protected void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            
            g.setColor(Color.white);
            g.drawRect(765, 400, 35, 95);
            g.drawRect(0, 300, 35, 95);
            g.drawRect(765, 200, 35, 95);
            g.drawRect(0, 100, 35, 95);
            g.drawRect(765, 0, 35, 95);
        }
    }  
    
        
    public JPanel getGameplayCenterPanel(){
        return this.gameplayCenterPanel;
    }
    
}
