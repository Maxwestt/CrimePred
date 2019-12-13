/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crimepred;

import java.awt.Graphics;
import java.awt.Graphics2D;
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
        
        
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(mapImage, 0, 0, this);
    }

    public void yeet() {
        System.out.println("yeet");
    }
}
