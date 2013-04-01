package lms.gui.member;

import lms.Config;
import lms.entity.Entity;
import lms.entity.Item;
import lms.entity.Member;
import lms.entity.Shelf;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShelfItemPanel extends EntityPanel{
	
	private Item item;
	
	private JLabel lblItemTitle;
	private JLabel lblItemIsbn;
	private JLabel lblItemAuthor;
	private JButton btnBorrow;
	private JButton btnReserve;
	private JButton btnRemove;
	public ShelfItemPanel() {
		setLayout(null);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setFont(new Font("Georgia", Font.ITALIC, 15));
		lblTitle.setBounds(169, 25, 46, 14);
		add(lblTitle);
		
		lblItemTitle = new JLabel("");
		lblItemTitle.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblItemTitle.setBounds(250, 19, 284, 25);
		add(lblItemTitle);
		
		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setFont(new Font("Liberation Serif", Font.PLAIN, 13));
		lblIsbn.setBounds(179, 55, 46, 14);
		add(lblIsbn);
		
		lblItemIsbn = new JLabel("");
		lblItemIsbn.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblItemIsbn.setBounds(248, 55, 145, 14);
		add(lblItemIsbn);
		
		JLabel lblAuthor = new JLabel("Author:");
		lblAuthor.setFont(new Font("Liberation Serif", Font.PLAIN, 13));
		lblAuthor.setBounds(181, 82, 46, 14);
		add(lblAuthor);
		
		lblItemAuthor = new JLabel("");
		lblItemAuthor.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblItemAuthor.setBounds(248, 82, 145, 14);
		add(lblItemAuthor);
		
		btnBorrow = new JButton("Borrow");
		btnBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Member member = (Member) frame.getLoginMgr().getUser(); 
					frame.getBorrowMgr().borrow(member, item);
					JOptionPane.showMessageDialog(null, "loan request has forwarded to check out counter", Config.TITLE_SUCCESS, JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, exception.getMessage(), Config.TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
					exception.printStackTrace();
				}
			}
		});
		btnBorrow.setBounds(467, 11, 89, 23);
		add(btnBorrow);
		
		btnReserve = new JButton("Reserve");
		btnReserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Member member = (Member) frame.getLoginMgr().getUser();
					frame.getReserveMgr().reserve(member, item);
					btnReserve.setText("Reserved");
					btnReserve.setEnabled(false);
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, exception.getMessage(), Config.TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
					exception.printStackTrace();
				}
			}
		});
		btnReserve.setBounds(467, 45, 89, 23);
		add(btnReserve);
		
		btnRemove = new JButton("Remove ");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String message = "Do you really want to remove from your shelf?";
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
		btnRemove.setBounds(467, 78, 89, 23);
		add(btnRemove);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 115, 600, 2);
		add(separator);
	}

	@Override
	public void setEntity(Entity entity) throws Exception {
		item = (Item) entity;
		if (!item.getInfo())
			throw new Exception("Item not exist");
		lblItemTitle.setText(item.getTitle());
		lblItemIsbn.setText(item.getIsbn());
		lblItemAuthor.setText(item.getAuthor());
		boolean reserved = frame.getReserveMgr().checkReserved((Member)frame.getLoginMgr().getUser(), item);
		btnReserve.setEnabled(!reserved);
		if (reserved)
			btnReserve.setText("Reserved");
	}

	@Override
	public void accept() throws Exception {
		frame.getShelfMgr().removeFromShelf((Member)frame.getLoginMgr().getUser(), item);
		setVisible(false);
		EntityPanelContainer.getContainer(this).reducePanelSize();
		getParent().remove(this);
	}

}
