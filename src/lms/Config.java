package lms;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Config {
	//Dialog infomation
	public static String TITLE_SUCCESS = "Success"; 
	public static String TITLE_ERROR = "ERROR";
	public static String TITLE_WARNING = "WARNING";
	
	//DataBase 
	public static String DATABASE_URL = "jdbc:mysql://localhost:3306/cz2006db";
	public static String DATABASE_USERNAME = "root";
	public static String DATABASE_PASSWORD = "root";
	
	// Member Part Constant
	public static int STUDENT_QUOTA = 2;
	public static int STUFF_QUOTA = 3;
	
	public static int STUDENT_RENEW_LIMIT = 3;
	public static int STUFF_RENEW_LIMIT =6;
	
	//Loan Part Constant
	public static int STUDENT_LOAN_DURATION = 7;
	public static int STUFF_LOAN_DURATION = 14;
	public static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	public static int FINE = 100;
	public static int RQUEST_DUE_DATE = 2;
	

	//Panel
	public static Color BEIGE = new Color(234, 224, 205);
	
	//item
	public static int IMAGE_WIDTH = 166;
	public static int IMAGE_HIGHT = 194;
	public static ImageIcon DEFAULT_IMAGE = new ImageIcon(getDefault(IMAGE_WIDTH,IMAGE_HIGHT));
	public static ImageIcon THUMBNAIL_DEFAULT_IMAGE = new ImageIcon(getDefault(IMAGE_WIDTH/2,IMAGE_HIGHT/2));
	
	//public method
	public static Image getDefault(int w, int h){
		Image bImage = null;
		try {                
	          bImage = ImageIO.read(new File("res/default.jpg"));//AssassinsCreed002.jpg
	       } catch (IOException ex) {
	           System.out.print("Cannot load search picture"); // handle exception...
	       }
		
		return changeSize(bImage,w,h);
	}
	
	public static Image changeSize(Image original, int w, int h){
		final BufferedImage scaled = new BufferedImage(w,h, BufferedImage.TYPE_INT_ARGB);
	    final Graphics2D g2d = scaled.createGraphics();
	    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2d.drawImage(original, 0, 0, w, h, 0, 0, original.getWidth(null), original.getHeight(null), null);
	    g2d.dispose();
		return scaled;
	}

}
