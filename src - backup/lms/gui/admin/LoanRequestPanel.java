package lms.gui.admin;

import lms.Config;
import lms.entity.Entity;
import lms.entity.Item;
import lms.entity.Loan;
import lms.gui.member.EntityPanel;
import lms.gui.member.EntityPanelContainer;
import lms.manager.MemberMgr;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class LoanRequestPanel extends EntityPanel {
	private JLabel lblTitle;
	private JLabel lblAuthor;
	private JLabel lblIsbn;
	
	private Loan loan;
	private JSeparator separator;
	
	
	public LoanRequestPanel() {
		setLayout(null);
		
		lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblTitle.setBounds(127, 11, 192, 22);
		add(lblTitle);
		
		lblAuthor = new JLabel("Author");
		lblAuthor.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblAuthor.setBounds(127, 33, 128, 22);
		add(lblAuthor);
		
		lblIsbn = new JLabel("Isbn");
		lblIsbn.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblIsbn.setBounds(127, 56, 138, 22);
		add(lblIsbn);
		
		JButton btnCheckOut = new JButton("Check Out");
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "Comfirm to Check Out?";
				int reply = JOptionPane.showConfirmDialog(null, message, Config.TITLE_WARNING, JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					try {
						accept();
					} catch (Exception exception) {
						exception.printStackTrace();
						JOptionPane.showMessageDialog(null, exception.getMessage(), Config.TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
					}
				}	
				
			}
		});
		btnCheckOut.setBounds(265, 34, 108, 23);
		add(btnCheckOut);
		
		JButton btnReject = new JButton("Reject");
		btnReject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "Comfirm to reject?";
				int reply = JOptionPane.showConfirmDialog(null, message, Config.TITLE_WARNING, JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					try {
						reject();
					} catch (Exception exception) {
						exception.printStackTrace();
						JOptionPane.showMessageDialog(null, exception.getMessage(), Config.TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
					}
				}	
			}
		});
		btnReject.setBounds(383, 34, 89, 23);
		add(btnReject);
		
		separator = new JSeparator();
		separator.setBounds(10, 87, 485, 5);
		add(separator);
	}

	@Override
	public void setEntity(Entity entity) throws Exception {
		loan = (Loan) entity;
		loan.getInfo();
		Item item = loan.getItem();
		item.getInfo();
		lblTitle.setText(item.getTitle());
		lblAuthor.setText(item.getAuthor());
		lblIsbn.setText(item.getIsbn());
	}

	@Override
	public void accept() throws Exception {
		frame.getCheckOutMgr().checkOut(loan);
		setVisible(false);
		EntityPanelContainer.getContainer(this).reducePanelSize();
		getParent().remove(this);
	}
	
	public void reject() throws Exception {
		frame.getCheckOutMgr().Reject(loan);
		setVisible(false);
		EntityPanelContainer.getContainer(this).reducePanelSize();
		getParent().remove(this);
	}
}
