/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crimepred;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author s141452
 */
public class mapPanel extends JPanel {

    private BufferedImage mapImage;
    
    public int circle_x1, circle_y1, circle_x2, circle_y2, 
            circle_x3, circle_y3 = 0; 
    public int circle_w, circle_h = 0;

    public mapPanel() {
        String path = "Resources/placeholder.PNG";
        ImageIcon ii = new ImageIcon(getClass().getClassLoader().getResource(path));
        mapImage = new BufferedImage(
                ii.getIconWidth(),
                ii.getIconHeight(),
                BufferedImage.TYPE_INT_RGB);
        Graphics g = mapImage.createGraphics();
        // paint the Icon to the BufferedImage.
        ii.paintIcon(null, g, 0, 0);
        g.dispose();
        
        makeCentres();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        g2.drawImage(mapImage, 0, 0, this);
        
        g2.setColor(Color.red);
        g2.setStroke(new BasicStroke(5));
        
        g2.drawOval(circle_x1, circle_y1, circle_w, circle_h);
        g2.drawOval(circle_x2, circle_y2, circle_w, circle_h);
        g2.drawOval(circle_x3, circle_y3, circle_w, circle_h);
    }
    
    public void makeCentres(){
        circle_x1 = 100;
        circle_y1 = 100;
        
        circle_x2 = 300;
        circle_y2 = 200;
        
        circle_x3 = 500;
        circle_y3 = 250;
        
        circle_w = 30;
        circle_h = 30;
    }
    
    public int[] returnCentres(){
        int[] centres = new int[6];
        
        centres[0] = circle_x1;
        centres[2] = circle_x2;
        centres[4] = circle_x3;
        
        centres[1] = circle_y1;
        centres[3] = circle_y2;
        centres[5] = circle_y3;
        
        return centres;
    }
}
