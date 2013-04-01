package lms.gui.member;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImageFilter;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import lms.Config;
import lms.Utils;
import lms.gui.ImagePanel;
import lms.gui.LmsFrame;


public class LoginPanel extends ImagePanel{
	private JTextField textFieldUsername;
	private JPasswordField passwordField;
	
	private LmsFrame frame;
	
    private JButton btnNewButton;
    private BufferedImage image;
    //private BufferedImage scaled;
    //private ImageIcon submit;
    //private ImageIcon submit2;
	
	public LoginPanel() {
		super("res/background.png");
		//this.setImage("res/background.png");
		
		setLayout(null);
		
		// username Label and Textfeild
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setFont(new Font("Chalkduster", Font.PLAIN, 14));
		lblUsername.setBounds(250, 66, 81, 26);
		lblUsername.setForeground(new Color(49,79,79));
		add(lblUsername);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setToolTipText("username");
		textFieldUsername.setBounds(350, 70, 158, 20);
		add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		//password Label and Passwordfeild
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Chalkduster", Font.PLAIN, 14));
		lblPassword.setBounds(250, 103, 81, 26);
		lblPassword.setForeground(new Color(49,79,79));
		add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setToolTipText("password");
		passwordField.setBounds(350, 107, 158, 20);
		add(passwordField);
		
		
		JLabel lblDomain = new JLabel("Domain:");
		lblDomain.setHorizontalAlignment(SwingConstants.CENTER);
		lblDomain.setFont(new Font("Chalkduster", Font.PLAIN, 14));
		lblDomain.setBounds(250, 140, 81, 26);
		lblDomain.setForeground(new Color(49,79,79));
		add(lblDomain);
		
		final JComboBox domainComboBox = new JComboBox();
		
		domainComboBox.setFont(new Font("Chalkduster", Font.PLAIN, 14));
		domainComboBox.setModel(new DefaultComboBoxModel(new String[] {"Member", "Admin"}));
		domainComboBox.setSelectedIndex(0);
		domainComboBox.setBounds(350, 139, 105, 24);
		domainComboBox.setForeground(new Color(49,79,79));
		add(domainComboBox);

		
		//ImageIcon submit = new ImageIcon("res/submit.png");
		
		try {                
	          image = ImageIO.read(new File("res/submit.png"));//AssassinsCreed002.jpg
	       } catch (IOException ex) {
	           System.out.print("Cannot load background picture"); // handle exception...
	       } 
	    
	    ImageIcon submit = new ImageIcon(changeSize(image,80,28));
		
		
		//ImageIcon.setSize()
		btnNewButton = new JButton(submit);
		MouseM mouseM= new MouseM();
		btnNewButton.addMouseListener(mouseM);
		btnNewButton.setContentAreaFilled(false);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String domain = (String) domainComboBox.getSelectedItem();
				String username = textFieldUsername.getText();
				String password = Utils.md5(passwordField.getText());
				
				try {
					frame.getLoginMgr().login(domain, username, password);
					frame.menuRefresh();
					if (domain.equals("Member")) {
						frame.getUserPanel().updateProfile();
						frame.changeMemberPanel("userPanel");
						frame.getUserPanel().changePanel("statusPanel");
					}
					else {
						frame.changeDomain("adminPanel");
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), Config.TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});
		//btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		btnNewButton.setBorder(null);
		btnNewButton.setBounds(350, 180, submit.getImage().getWidth(null), submit.getImage().getHeight(null));
		add(btnNewButton);
		
		
		
	}
	
	public class MouseM extends MouseAdapter {
		@Override
		public void mouseEntered(MouseEvent arg0) {
			ImageIcon submit2 = new ImageIcon("res/submit2.png");
			btnNewButton.setIcon(submit2);
			//btnNewButton = JButton(submit);	
		}
		
		public void mouseExited(MouseEvent arg0) {
			ImageIcon submit1 = new ImageIcon(changeSize(image,80,28));
			btnNewButton.setIcon(submit1);
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
