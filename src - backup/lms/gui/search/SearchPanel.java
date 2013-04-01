package lms.gui.search;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;


import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JSplitPane;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.UIManager;

import lms.Config;
import lms.entity.Item;
import lms.gui.ImagePanel;
import lms.gui.LmsFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import lms.gui.member.LoginPanel.MouseM;

public class SearchPanel extends ImagePanel {
	private LmsFrame frame;
	
	private JTextField textFieldSearch;
	private JCheckBox chckbxTitle;
	private JCheckBox chckbxAuthor;
	private JCheckBox chckbxIsbn;
	private JButton btnSearch;
	private BufferedImage image;
	private BufferedImage image2;
	
	public SearchPanel() {
		super("res/bar.png");
		setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Config.BEIGE);
		add(topPanel);
		topPanel.setOpaque(false);
		
		textFieldSearch = new JTextField();
		topPanel.add(textFieldSearch);
		textFieldSearch.setColumns(25);
		
		
		//ImageIcon search = new ImageIcon("res/search.png");
		
		try {                
	          image = ImageIO.read(new File("res/search.png"));//AssassinsCreed002.jpg
	       } catch (IOException ex) {
	           System.out.print("Cannot load search picture"); // handle exception...
	       }
		
		try {                
	          image2 = ImageIO.read(new File("res/search2.png"));//AssassinsCreed002.jpg
	       } catch (IOException ex) {
	           System.out.print("Cannot load search picture"); // handle exception...
	       }
		
	    
	    ImageIcon search = new ImageIcon(changeSize(image,85,25));
		
		//ImageIcon.setSize()
		btnSearch = new JButton(search);
		MouseM mouseM= new MouseM();
		btnSearch.addMouseListener(mouseM);
		btnSearch.setContentAreaFilled(false);
		
		//btnSearch = new JButton(search);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String keyword = textFieldSearch.getText();
					ArrayList<String> searchArea = new ArrayList<String>();
					if (chckbxTitle.isSelected())
						searchArea.add("title");
					if (chckbxAuthor.isSelected())
						searchArea.add("author");
					if (chckbxIsbn.isSelected())
						searchArea.add("isbn");
					ArrayList<Item> result = frame.getSearchMgr().getSearchResult(keyword, searchArea);
					frame.getMainSearchPanel().getSearchItemContainer().setPanels(result);
					frame.changeMemberPanel("mainSearchPanel");
					frame.getMainSearchPanel().ChangePanel("searchItemContainer");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), Config.TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});
		btnSearch.setBorder(null);
		//btnSearch.setBounds(290, 195, search.getImage().getWidth(null), search.getImage().getHeight(null));
		topPanel.add(btnSearch);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(Config.BEIGE);
		add(bottomPanel);
		bottomPanel.setOpaque(false);
		
		chckbxIsbn = new JCheckBox("ISBN");
		chckbxIsbn.setBackground(new Color(234, 224, 205));
		bottomPanel.add(chckbxIsbn);
		chckbxIsbn.setSelected(true);
		chckbxIsbn.setOpaque(false);
		chckbxIsbn.setFont(new Font("Chalkboard", Font.PLAIN, 14));
		
		chckbxTitle = new JCheckBox("Title");
		chckbxTitle.setBackground(Config.BEIGE);		
		bottomPanel.add(chckbxTitle);
		chckbxTitle.setOpaque(false);
		chckbxTitle.setFont(new Font("Chalkboard", Font.PLAIN, 14));
		
		chckbxAuthor = new JCheckBox("Author");
		chckbxAuthor.setBackground(Config.BEIGE);
		chckbxAuthor.setOpaque(false);
		chckbxAuthor.setFont(new Font("Chalkboard", Font.PLAIN, 14));
		bottomPanel.add(chckbxAuthor);
		
	}
	
	public class MouseM extends MouseAdapter {
		@Override
		public void mouseEntered(MouseEvent arg0) {
			//ImageIcon search2 = new ImageIcon("res/search2.png");
			ImageIcon search2 = new ImageIcon(changeSize(image2,85,25));
			btnSearch.setIcon(search2);
			//btnNewButton = JButton(submit);	
		}
		
		public void mouseExited(MouseEvent arg0) {
			//ImageIcon search1 = new ImageIcon("res/saerch.png");
			ImageIcon search1 = new ImageIcon(changeSize(image,85,25));
			btnSearch.setIcon(search1);
			//btnNewButton = JButton(submit);
		}
	}
	
	public Image changeSize(Image original, int w, int h){
		final BufferedImage scaled = new BufferedImage(w,h, BufferedImage.TYPE_INT_ARGB);
	    final Graphics2D g2d = scaled.createGraphics();
	    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2d.drawImage(original, 0, 0, w, h, 0, 0, original.getWidth(null), original.getHeight(null), null);
	    g2d.dispose();
		return scaled;
	}
	
	
	public void init() {
		frame = LmsFrame.GetLmsFrame(this);
	}

}
