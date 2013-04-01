package lms.gui.admin;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import lms.entity.Item;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AdminItemUpdatePanel  extends AdminEntityUpdatePanel{
	private Item item;
	
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTextField txtIsbn;
	private JTextField txtCopy;
	private JTextArea txtaAbstract;
	private JComboBox comboBox;
	
	public AdminItemUpdatePanel() {
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setFont(new Font("STZhongsong", Font.PLAIN, 15));
		lblTitle.setBounds(204, 21, 77, 21);
		add(lblTitle);
		
		JLabel lblAuthor = new JLabel("Author:");
		lblAuthor.setFont(new Font("STZhongsong", Font.PLAIN, 15));
		lblAuthor.setBounds(204, 81, 77, 21);
		add(lblAuthor);
		
		JLabel lblIsbn = new JLabel("Isbn:");
		lblIsbn.setFont(new Font("STZhongsong", Font.PLAIN, 15));
		lblIsbn.setBounds(204, 113, 77, 21);
		add(lblIsbn);
		
		JLabel lblCopy = new JLabel("Copy:");
		lblCopy.setFont(new Font("STZhongsong", Font.PLAIN, 15));
		lblCopy.setBounds(204, 145, 77, 21);
		add(lblCopy);
		
		JLabel lblAbstract = new JLabel("Abstract:");
		lblAbstract.setFont(new Font("STZhongsong", Font.PLAIN, 15));
		lblAbstract.setBounds(204, 177, 77, 21);
		add(lblAbstract);
		
		txtTitle = new JTextField();
		txtTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTitle.setBounds(324, 21, 208, 20);
		add(txtTitle);
		txtTitle.setColumns(10);
		
		txtAuthor = new JTextField();
		txtAuthor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtAuthor.setBounds(324, 81, 208, 20);
		add(txtAuthor);
		txtAuthor.setColumns(10);
		
		txtIsbn = new JTextField();
		txtIsbn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtIsbn.setEditable(false);
		txtIsbn.setColumns(10);
		txtIsbn.setBounds(324, 113, 208, 20);
		add(txtIsbn);
		
		txtCopy = new JTextField();
		txtCopy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCopy.setColumns(10);
		txtCopy.setBounds(324, 145, 208, 20);
		add(txtCopy);
		
		txtaAbstract = new JTextArea();
		txtaAbstract.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtaAbstract.setBounds(324, 177, 208, 96);
		txtaAbstract.setLineWrap(true);
		txtaAbstract.setWrapStyleWord(true);
		add(txtaAbstract);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setFont(new Font("STZhongsong", Font.PLAIN, 15));
		lblType.setBounds(204, 53, 77, 21);
		add(lblType);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("YouYuan", Font.PLAIN, 16));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Book", "CD"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(324, 52, 77, 21);
		add(comboBox);
		
	}
	
	public void setItem(Item item) {
		this.item = item;
		txtTitle.setText(item.getTitle());
		txtAuthor.setText(item.getAuthor());
		txtIsbn.setText(item.getIsbn());
		txtCopy.setText(((Integer)item.getQuantity()).toString());
		txtaAbstract.setText(item.getDescription());
		comboBox.setSelectedItem(item.getType());
	}

	@Override
	public void accept() throws Exception {
		item.setAuthor(txtAuthor.getText());
		item.setDescription(txtaAbstract.getText());
		item.setQuantity(Integer.parseInt(txtCopy.getText()));
		item.setTitile(txtTitle.getText());
		item.setType((String)comboBox.getSelectedItem());
		frame.getItemMgr().addItem(item);
	}

	@Override
	public void cancel() {
		frame.getAdminPanel().changePanel("adminNaviPanel");
	}
}
