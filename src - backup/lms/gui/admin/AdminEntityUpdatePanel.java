package lms.gui.admin;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import lms.Config;
import lms.gui.LmsFrame;

public abstract class AdminEntityUpdatePanel extends JPanel {
	protected LmsFrame frame;
	protected JButton btnSubmit;
	
	public AdminEntityUpdatePanel() {
		setLayout(null);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String message = "Comfirm to proceed?";
					int reply = JOptionPane.showConfirmDialog(null, message, Config.TITLE_WARNING, JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						accept();
						JOptionPane.showMessageDialog(null, "operation succeeded", Config.TITLE_SUCCESS, JOptionPane.INFORMATION_MESSAGE);
						frame.getAdminPanel().changePanel("adminNaviPanel");
					}
				
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, exception.getMessage(), Config.TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
					exception.printStackTrace();
				}
			}
		});
		btnSubmit.setBounds(327, 298, 89, 23);
		add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		btnCancel.setBounds(471, 298, 89, 23);
		add(btnCancel);
	}
	
	public void init() {
		frame = LmsFrame.GetLmsFrame(this);	
	}
	public abstract void accept() throws Exception;
	public void cancel() {
		frame.getAdminPanel().changePanel("adminNaviPanel");
	}

}
