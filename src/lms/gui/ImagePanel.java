package lms.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

    private BufferedImage image;

    public ImagePanel(String str) {
       try {                
          image = ImageIO.read(new File(str));//AssassinsCreed002.jpg
    	   //image = ImageIO.read(new File());
       } catch (IOException ex) {
           System.out.print("Cannot load background picture"); // handle exception...
       }
    }
    
 

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this.getWidth(),this.getHeight(), null); // see javadoc for more info on the parameters            
    }

}