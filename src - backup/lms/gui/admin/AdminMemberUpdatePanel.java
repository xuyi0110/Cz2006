package lms.gui.admin;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import lms.entity.Member;

public class AdminMemberUpdatePanel extends AdminEntityUpdatePanel {
	private JTextField txtName;
	private JTextField txtCourse;
	private JTextField txtYear;
	private JTextField txtMid;
	private JComboBox comboBox;
	
	private Member member;
	private JLabel lblPassword;
	private JTextField txtPassword;
	private JLabel lblConfirmPassword;
	private JTextField txtConfirmPassword;
	public AdminMemberUpdatePanel() {
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("STZhongsong", Font.PLAIN, 15));
		lblName.setBounds(204, 43, 77, 21);
		add(lblName);
		
		JLabel lblCourse = new JLabel("Course:");
		lblCourse.setFont(new Font("STZhongsong", Font.PLAIN, 15));
		lblCourse.setBounds(204, 75, 77, 21);
		add(lblCourse);
		
		JLabel lblYear = new JLabel("Year:");
		lblYear.setFont(new Font("STZhongsong", Font.PLAIN, 15));
		lblYear.setBounds(204, 107, 77, 21);
		add(lblYear);
		
		JLabel lblMid = new JLabel("Member ID:");
		lblMid.setFont(new Font("STZhongsong", Font.PLAIN, 15));
		lblMid.setBounds(204, 139, 89, 21);
		add(lblMid);
		
		JLabel lblIndentity = new JLabel("Indentity:");
		lblIndentity.setFont(new Font("STZhongsong", Font.PLAIN, 15));
		lblIndentity.setBounds(204, 235, 77, 21);
		add(lblIndentity);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtName.setColumns(10);
		txtName.setBounds(352, 43, 208, 20);
		add(txtName);
		
		txtCourse = new JTextField();
		txtCourse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCourse.setColumns(10);
		txtCourse.setBounds(352, 75, 208, 20);
		add(txtCourse);
		
		txtYear = new JTextField();
		txtYear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtYear.setColumns(10);
		txtYear.setBounds(352, 107, 208, 20);
		add(txtYear);
		
		txtMid = new JTextField();
		txtMid.setEditable(false);
		txtMid.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMid.setColumns(10);
		txtMid.setBounds(352, 138, 208, 23);
		add(txtMid);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Student", "Staff"}));
		comboBox.setFont(new Font("YouYuan", Font.PLAIN, 16));
		comboBox.setBounds(352, 236, 89, 21);
		add(comboBox);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("STZhongsong", Font.PLAIN, 15));
		lblPassword.setBounds(204, 171, 89, 21);
		add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPassword.setColumns(10);
		txtPassword.setBounds(352, 171, 208, 20);
		add(txtPassword);
		
		lblConfirmPassword = new JLabel("Reenter Password:");
		lblConfirmPassword.setFont(new Font("STZhongsong", Font.PLAIN, 15));
		lblConfirmPassword.setBounds(204, 203, 154, 21);
		add(lblConfirmPassword);
		
		txtConfirmPassword = new JPasswordField();
		txtConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtConfirmPassword.setColumns(10);
		txtConfirmPassword.setBounds(352, 202, 208, 20);
		add(txtConfirmPassword);
	}

	@Override
	public void accept() throws Exception {
		if (!txtPassword.getText().equals(txtConfirmPassword.getText()))
			throw new Exception("Password not same");
		member.setName(txtName.getText());
		member.setCourse(txtCourse.getText());
		member.setYear(Integer.parseInt(txtYear.getText()));
		member.setType((String)comboBox.getSelectedItem());
		frame.getMemberMgr().addMember(member);
		String password = txtPassword.getText();
		if ( password!= null && !password.equals("")) {
			frame.getMemberMgr().addAccount(member, password);
		}
	}
	
	public void setMember(Member member) {
		this.member = member;
		txtName.setText(member.getName());
		txtCourse.setText(member.getCourse());
		//System.out.println("here");
		txtYear.setText(((Integer) member.getYear()).toString());
		//System.out.println("here");
		txtMid.setText(member.getId());
		//System.out.println("here");
		comboBox.setSelectedItem(member.getType());
		//System.out.println("Set complete");
	}

}
