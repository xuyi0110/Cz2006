package lms.gui.admin;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import lms.Config;
import lms.gui.LmsFrame;

import javax.swing.JButton;

public abstract class EntitySearchPanel extends JPanel{
	protected LmsFrame frame;
	
	protected JTextField textField;
	private JLabel lblSearchinfo;
	private JButton btnBack;
	private JButton btnProceed;
	
	public EntitySearchPanel() {
		setLayout(null);
		
		
		
		
		lblSearchinfo = new JLabel("searchInfo:");
		lblSearchinfo.setBounds(143,34,164,32);
		lblSearchinfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchinfo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblSearchinfo);
		
		textField = new JTextField();
		textField.setBounds(334,41, 198, 23);
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (textField.getText() == null)
						JOptionPane.showMessageDialog(null, "The search text cannot be empty", Config.TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
					else
						accept();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), Config.TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});
		
		add(textField);
		textField.setColumns(25);
		
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getAdminPanel().changePanel("adminNaviPanel");
			}
		});
		btnBack.setBounds(514, 315, 87, 23);
		add(btnBack);
		
		btnProceed = new JButton("Proceed");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				try {
					if (textField.getText() == null)
						JOptionPane.showMessageDialog(null, "The search text cannot be empty", Config.TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
					else
						accept();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), Config.TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});
		btnProceed.setBounds(408, 315, 96, 23);
		add(btnProceed);
	}
	public void setSearchInfo(String str) {
		lblSearchinfo.setText(str + "    ");
	}
	
	public abstract void accept() throws Exception;
	
	public void init() {
		frame = (LmsFrame) LmsFrame.GetLmsFrame(this);
	}

}
