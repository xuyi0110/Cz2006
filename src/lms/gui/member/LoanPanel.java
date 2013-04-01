package lms.gui.member;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

import lms.Config;
import lms.entity.Entity;
import lms.entity.Item;
import lms.entity.Loan;
import lms.entity.Member;

import javax.swing.JSeparator;

public class LoanPanel extends EntityPanel{
	private Loan loan;
	
	private JLabel lblItemTitle;
	private JLabel lblItemIsbn;
	private JLabel lblItemAuthor;
	private JLabel lblLoanBorrowedDate;
	private JLabel lblLoanDueDate;
	private JLabel lblLoanRenewedTimes;
	private JLabel lblLoanStatus;
	final JCheckBox chckbxSelect;
	private JButton btnRenew;
	
	private boolean seleted;
	
	public LoanPanel() {
		setLayout(null);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setFont(new Font("Georgia", Font.ITALIC, 15));
		lblTitle.setBounds(32, 11, 46, 14);
		add(lblTitle);
		
		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setFont(new Font("Liberation Serif", Font.PLAIN, 13));
		lblIsbn.setBounds(68, 40, 46, 14);
		add(lblIsbn);
		
		JLabel lblAuthor = new JLabel("Author:");
		lblAuthor.setFont(new Font("Liberation Serif", Font.PLAIN, 13));
		lblAuthor.setBounds(68, 65, 46, 14);
		add(lblAuthor);
		
		lblItemTitle = new JLabel("");
		lblItemTitle.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblItemTitle.setBounds(133, 0, 284, 25);
		add(lblItemTitle);
		
		lblItemIsbn = new JLabel("");
		lblItemIsbn.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblItemIsbn.setBounds(120, 40, 145, 14);
		add(lblItemIsbn);
		
		lblItemAuthor = new JLabel("");
		lblItemAuthor.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblItemAuthor.setBounds(120, 65, 145, 14);
		add(lblItemAuthor);
		
		JLabel lblBorrowedDate = new JLabel("Borrowed Date:");
		lblBorrowedDate.setFont(new Font("Liberation Serif", Font.PLAIN, 13));
		lblBorrowedDate.setBounds(275, 40, 97, 14);
		add(lblBorrowedDate);
		
		JLabel lblDueDate = new JLabel("Due Date:");
		lblDueDate.setFont(new Font("Liberation Serif", Font.PLAIN, 13));
		lblDueDate.setBounds(275, 65, 97, 14);
		add(lblDueDate);
		
		JLabel lblRenewedTimes = new JLabel("Renewed Times:");
		lblRenewedTimes.setFont(new Font("Liberation Serif", Font.PLAIN, 13));
		lblRenewedTimes.setBounds(275, 90, 97, 14);
		add(lblRenewedTimes);
		
		lblLoanBorrowedDate = new JLabel("");
		lblLoanBorrowedDate.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblLoanBorrowedDate.setBounds(386, 40, 203, 14);
		add(lblLoanBorrowedDate);
		
		lblLoanDueDate = new JLabel("");
		lblLoanDueDate.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblLoanDueDate.setBounds(386, 65, 204, 14);
		add(lblLoanDueDate);
		
		lblLoanRenewedTimes = new JLabel("");
		lblLoanRenewedTimes.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblLoanRenewedTimes.setBounds(386, 90, 145, 14);
		add(lblLoanRenewedTimes);
		
		btnRenew = new JButton("Renew");
		btnRenew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String message = "Do you really want to renew?";
					int reply = JOptionPane.showConfirmDialog(null, message, Config.TITLE_WARNING, JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						accept();
					}	
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.getMessage(), Config.TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnRenew.setBounds(508, 86, 89, 23);
		add(btnRenew);
		
		chckbxSelect = new JCheckBox("Select");
		chckbxSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleted = chckbxSelect.isSelected();
			}
		});
		chckbxSelect.setBounds(479, 8, 97, 23);
		add(chckbxSelect);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 115, 600, 2);
		add(separator);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Liberation Serif", Font.PLAIN, 13));
		lblStatus.setBounds(68, 90, 46, 14);
		add(lblStatus);
		
		lblLoanStatus = new JLabel("");
		lblLoanStatus.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblLoanStatus.setBounds(120, 90, 145, 14);
		add(lblLoanStatus);
		
		seleted = false;
	}
	@Override
	public void setEntity(Entity entity) throws Exception {
		loan = (Loan) entity;
		Member borrower = loan.getBorrower();
		borrower.getInfo();
		Item item = loan.getItem();
		item.getInfo();
		
		lblItemTitle.setText(item.getTitle());
		lblItemIsbn.setText(item.getIsbn());
		lblItemAuthor.setText(item.getAuthor());
		lblLoanBorrowedDate.setText(Config.DATE_FORMAT.format(loan.getBorrowedDate()));
		int loanDuration = borrower.getLoanDuration();
		
		Date dueDate = new Date(loan.getBorrowedDate().getTime() + loanDuration * (24 * 60 * 60 * 1000));
		lblLoanDueDate.setText(Config.DATE_FORMAT.format(dueDate));
		Date currentDate = new Date();
		if (currentDate.getTime() > dueDate.getTime()) {
			lblLoanDueDate.setForeground(Color.RED);
			lblLoanStatus.setForeground(Color.RED);
			lblLoanStatus.setText("Overdued");
			lblLoanStatus.setFont(new Font("SansSerif", Font.BOLD, 12));
			chckbxSelect.setEnabled(false);
			btnRenew.setEnabled(false);
		} else {
			lblLoanStatus.setText("Normal");
		}
		
		Integer renewedTimes = loan.getRenewedTimes();
		
		lblLoanRenewedTimes.setText(renewedTimes.toString());
		if (renewedTimes >= borrower.getRenewLimit()) {
			chckbxSelect.setEnabled(false);
			btnRenew.setEnabled(false);
		} 
	}
	
	public boolean isSeleted() {
		return seleted;
	}
	

	@Override
	public void accept() throws Exception {
		frame.getRenewMgr().renew(loan);
		setEntity(loan);
	}
}
