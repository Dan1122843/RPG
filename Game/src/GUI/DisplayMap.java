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

    public int getHorisMove() {
        return horisMove;
    }

    public void setHorisMove(int horisMove) {
        this.horisMove = horisMove;
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
    
    class DrawCharacter extends JPanel{

        BufferedImage warrior;
        BufferedImage elf;
        BufferedImage dwarf;
        
        DrawCharacter() throws IOException {
            this.warrior = ImageIO.read(new File("./warrior.png"));
            this.elf = ImageIO.read(new File("./elf.png"));
            this.dwarf = ImageIO.read(new File("./dwarf.png"));
            this.setPreferredSize(new Dimension(50, 50));
            
        }
	
	public Dimension getPreferredSize() {
		return new Dimension(800, 500);
	}
        	
	public void paintComponent(Graphics g) {
            
            Component c = getGameplayCenterPanel();
            paintBorder(c, g, 0, 0, 800, 500);
            if(GameSetup.getCharacter() == 1) 
                g.drawImage(warrior, getHorisMove(), 200, 100, 100, c);
            if(GameSetup.getCharacter() == 2) 
                g.drawImage(elf, getHorisMove(), 200, 100, 100, c);
            if(GameSetup.getCharacter() == 3) 
                g.drawImage(dwarf, getHorisMove(), 200, 100, 100, c);
            
                         
	}
        
               
        protected void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

            g.setColor(Color.pink);

            //  Draw rectangles around the component, but do not draw
            //  in the component area itself.
            g.fillRect(x, y, width, 5); 
            g.fillRect(x, y, 5, height); 
            g.fillRect(x+width-5, y, 5, height); 
            g.fillRect(x, y+height-10, width, 5);
            
            g.setColor(Color.white);
            g.drawRect(780, 200, 20, 100);
        }
    }  
    
        
    public JPanel getGameplayCenterPanel(){
        return this.gameplayCenterPanel;
    }
    
}


