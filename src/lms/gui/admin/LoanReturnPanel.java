package lms.gui.admin;

import lms.Config;
import lms.entity.Entity;
import lms.entity.Item;
import lms.entity.Loan;
import lms.entity.Member;
import lms.gui.member.EntityPanel;
import lms.gui.member.EntityPanelContainer;
import lms.manager.ReturnMgr;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoanReturnPanel extends EntityPanel {
	private JLabel lblItemTitle;
	private JLabel lblItemIsbn;
	private JLabel lblItemAuthor;
	private JLabel lblLoanBorrowedDate;
	private JLabel lblLoanDueDate;
	private JLabel lblLoanFine;
	private JLabel lblLoanStatus;
	private JButton btnReturn;
	
	private Loan loan;
	
	public LoanReturnPanel() {
		setLayout(null);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setFont(new Font("Georgia", Font.ITALIC, 15));
		lblTitle.setBounds(10, 4, 46, 14);
		add(lblTitle);
		
		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setFont(new Font("Liberation Serif", Font.PLAIN, 13));
		lblIsbn.setBounds(36, 29, 46, 14);
		add(lblIsbn);
		
		JLabel lblAuthor = new JLabel("Author:");
		lblAuthor.setFont(new Font("Liberation Serif", Font.PLAIN, 13));
		lblAuthor.setBounds(36, 54, 46, 14);
		add(lblAuthor);
		
		lblItemTitle = new JLabel("");
		lblItemTitle.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblItemTitle.setBounds(56, 0, 284, 25);
		add(lblItemTitle);
		
		lblItemIsbn = new JLabel("");
		lblItemIsbn.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblItemIsbn.setBounds(88, 29, 145, 14);
		add(lblItemIsbn);
		
		lblItemAuthor = new JLabel("");
		lblItemAuthor.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblItemAuthor.setBounds(88, 54, 145, 14);
		add(lblItemAuthor);
		
		JLabel lblBorrowedDate = new JLabel("Borrowed Date:");
		lblBorrowedDate.setFont(new Font("Liberation Serif", Font.PLAIN, 13));
		lblBorrowedDate.setBounds(243, 29, 97, 14);
		add(lblBorrowedDate);
		
		JLabel lblDueDate = new JLabel("Due Date:");
		lblDueDate.setFont(new Font("Liberation Serif", Font.PLAIN, 13));
		lblDueDate.setBounds(243, 54, 97, 14);
		add(lblDueDate);
		
		JLabel lblFine = new JLabel("Fine:");
		lblFine.setFont(new Font("Liberation Serif", Font.PLAIN, 13));
		lblFine.setBounds(243, 79, 52, 14);
		add(lblFine);
		
		lblLoanBorrowedDate = new JLabel("");
		lblLoanBorrowedDate.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblLoanBorrowedDate.setBounds(355, 29, 203, 14);
		add(lblLoanBorrowedDate);
		
		lblLoanDueDate = new JLabel("");
		lblLoanDueDate.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblLoanDueDate.setBounds(355, 54, 204, 14);
		add(lblLoanDueDate);
		
		lblLoanFine = new JLabel("");
		lblLoanFine.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblLoanFine.setBounds(319, 79, 145, 14);
		add(lblLoanFine);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Liberation Serif", Font.PLAIN, 13));
		lblStatus.setBounds(36, 79, 46, 14);
		add(lblStatus);
		
		lblLoanStatus = new JLabel("");
		lblLoanStatus.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblLoanStatus.setBounds(88, 79, 145, 14);
		add(lblLoanStatus);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 100, 485, 5);
		add(separator);
		
		btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String message = "Confirm to Return?";
					int reply = JOptionPane.showConfirmDialog(null, message, Config.TITLE_WARNING, JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						accept();
					}
				} catch (Exception exception) {
					exception.printStackTrace();
					JOptionPane.showMessageDialog(null, exception.getMessage(), Config.TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnReturn.setBounds(405, 75, 89, 23);
		add(btnReturn);
	}

	@Override
	public void accept() throws Exception {
		ReturnMgr returnMgr = new ReturnMgr();
		if (!returnMgr.returnItem(loan)) {
			String message = "Is $" + loan.getFine() + " fine paid?";
			message += "\n Yes to confirm.";
			message += "\n No to cancel";
			message += "\nOnly after resetting fine can the book be returned";
			int reply = JOptionPane.showConfirmDialog(null, message, Config.TITLE_WARNING, JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.NO_OPTION) 
				return;
			returnMgr.confirmPay(loan);
		}
		setVisible(false);
		EntityPanelContainer.getContainer(this).reducePanelSize();
		getParent().remove(this);
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
		} else {
			lblLoanStatus.setText("Normal");
		}
		Double fine = loan.getFine();
		lblLoanFine.setText(fine.toString()); 
		if (fine>0) {
			lblLoanFine.setForeground(Color.RED);
		}
	}
}
