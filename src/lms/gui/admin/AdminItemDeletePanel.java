package lms.gui.admin;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

import lms.entity.Item;

public class AdminItemDeletePanel extends AdminEntityUpdatePanel {
	
	private JLabel lblTitle;
	private JLabel lblItemTotalCopy;
	private JLabel lblItemNoOutstanding;
	private JLabel lblItemNoReserved;
	private JLabel lblAuthor;
	private JLabel lblIsbn;
	private JLabel lblAbstract;
	private JLabel lblType;
	
	private Item item;
	
	
	public AdminItemDeletePanel() {
		
		JLabel lblBookDetail = new JLabel("Item Detail");
		lblBookDetail.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblBookDetail.setBounds(114, 23, 144, 24);
		add(lblBookDetail);
		
		lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTitle.setBounds(114, 161, 170, 14);
		add(lblTitle);
		
		lblAuthor = new JLabel("Author");
		lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAuthor.setBounds(114, 186, 144, 14);
		add(lblAuthor);
		
		lblIsbn = new JLabel("Isbn");
		lblIsbn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblIsbn.setBounds(114, 211, 144, 14);
		add(lblIsbn);
		
		lblAbstract = new JLabel("<html>Please remember not to leave the conert until it ends</html>");
		lblAbstract.setVerticalAlignment(SwingConstants.TOP);
		lblAbstract.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAbstract.setBounds(114, 261, 144, 77);
		add(lblAbstract);
		
		JLabel lblItemStatus = new JLabel("Item Status");
		lblItemStatus.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblItemStatus.setBounds(342, 23, 144, 24);
		add(lblItemStatus);
		
		JLabel lblTotalCopy = new JLabel("TotalCopy:");
		lblTotalCopy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalCopy.setBounds(378, 106, 84, 24);
		add(lblTotalCopy);
		
		lblType = new JLabel("Type");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblType.setBounds(114, 236, 144, 14);
		add(lblType);
		
		JLabel lblNoOutstanding = new JLabel("No. Outstanding:");
		lblNoOutstanding.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNoOutstanding.setBounds(378, 141, 108, 24);
		add(lblNoOutstanding);
		
		JLabel lblNoReserved = new JLabel("No. Reserved:");
		lblNoReserved.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNoReserved.setBounds(378, 176, 108, 24);
		add(lblNoReserved);
		
		lblItemTotalCopy = new JLabel("");
		lblItemTotalCopy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblItemTotalCopy.setBounds(496, 106, 84, 24);
		add(lblItemTotalCopy);
		
		lblItemNoOutstanding = new JLabel("");
		lblItemNoOutstanding.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblItemNoOutstanding.setBounds(496, 141, 84, 24);
		add(lblItemNoOutstanding);
		
		lblItemNoReserved = new JLabel("");
		lblItemNoReserved.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblItemNoReserved.setBounds(496, 176, 84, 24);
		add(lblItemNoReserved);
	}
	
	public void setItem(Item item) {
		this.item = item;
		lblTitle.setText(item.getTitle());
		lblAuthor.setText(item.getAuthor());
		lblIsbn.setText(item.getIsbn());
		lblType.setText(item.getType());
		lblAbstract.setText(item.getDescription());
		lblItemTotalCopy.setText(((Integer)item.getQuantity()).toString());
		lblItemNoOutstanding.setText(((Integer)item.getNoOutstanding()).toString());
		lblItemNoReserved.setText(((Integer)item.getNoReserved()).toString());
		if (item.getNoOutstanding()>0) {
			btnSubmit.setEnabled(false);
			lblItemNoOutstanding.setForeground(Color.RED);
		}
		else {
			btnSubmit.setEnabled(true);
			lblItemNoOutstanding.setForeground(Color.BLACK);	
		}
	}

	@Override
	public void accept() throws Exception {
		frame.getItemMgr().deleteItem(item);
		
	}

	@Override
	public void cancel() {
		frame.getAdminPanel().changePanel("adminNaviPanel");
	}

}
