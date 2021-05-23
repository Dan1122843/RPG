/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author liamo
 */
public class DrawEllipses {
    
    final static BasicStroke stroke = new BasicStroke(4.0f); //For ellipse
    
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(Color.WHITE);
        int x = 80;
        int y = 40;
        
        g2.setStroke(stroke);
        g2.draw(new Ellipse2D.Double(x, y, 200, 200));
    }
    
}
