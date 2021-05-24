/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import rpgCharacters.*;

/**
 *
 * @author Liam O'Connor
 */
public class DisplayMap {
    
    private JPanel gameplayCenterPanel; //Make equal to panel 5 in GameSetup
    
    
    
    
    public DisplayMap() throws IOException{
        this.gameplayCenterPanel = new JPanel();
        printMap(this.gameplayCenterPanel);
        
    }
    
    public void printMap(JPanel gameplayCenterPanel){
        gameplayCenterPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        gameplayCenterPanel.setBackground(new Color(10, 20, 50));
        gameplayCenterPanel.add(new DrawLines());
        
    }
    
    class DrawLines extends JPanel {
	
	public Dimension getPreferredSize() {
		return new Dimension(800, 500);
	}
	
	public void paintComponent(Graphics g) {
 
            Component c = getGameplayCenterPanel();
            paintBorder(c, g, 0, 0, 800, 500);
            g.setColor(Color.red);

            // X Start, Y Start, X End, Y End
            //Where Panel = width: 800 height: 500
            for(int i = 0; i < 5; i++){
                for(int j = 500; j >= 0; j = j - 100){
                    g.drawLine(0, j, 800, j);
                }
            }   
	}
        
        protected void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

            g.setColor(Color.pink);

            //  Draw rectangles around the component, but do not draw
            //  in the component area itself.
            g.fillRect(x, y, width, 5); 
            g.fillRect(x, y, 5, height); 
            g.fillRect(x+width-5, y, 5, height); 
            g.fillRect(x, y+height-10, width, 5);
        }
    }  
    

    
    public JPanel getGameplayCenterPanel(){
        return this.gameplayCenterPanel;
    }
    
}

