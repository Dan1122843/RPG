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
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import rpgCharacters.*;
import rpgMap.Map;
import sun.java2d.loops.DrawLine;
/**
 *
 * @author Liam O'Connor
 */
public class DisplayMap {
    
    private JPanel panel5; //Make equal to panel 5 in GameSetup
    
    
    
    
    public DisplayMap() throws IOException{
        this.panel5 = new JPanel();
        printMap(this.panel5);
        
    }
    
    public void printMap(JPanel panel5){
        panel5.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel5.setBackground(new Color(10, 20, 50));
        panel5.add(new DrawLines());
        
    }
    
    class DrawLines extends JPanel {
	
	public Dimension getPreferredSize() {
		return new Dimension(800, 500);
	}
	
	public void paintComponent(Graphics g) {
 
            Component c = getPanel5();
            paintBorder(c, g, 0, 0, 800, 500);
            g.setColor(Color.red);

            // X Start, Y Start, X End, Y End
            //Where Panel = width: 800 height: 600
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
    
    
    
    
    public JPanel getPanel5(){
        return this.panel5;
    }
    
}
