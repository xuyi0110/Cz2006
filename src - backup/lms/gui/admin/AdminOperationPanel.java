package lms.gui.admin;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JSeparator;

import lms.entity.Loan;
import lms.entity.Member;
import lms.gui.LmsFrame;
import lms.gui.member.EntityPanelContainer;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminOperationPanel extends JPanel {
	
	private LmsFrame frame;
	
	private Member member;
	
	private EntityPanelContainer entityPanelContainer;
	
	private JLabel lblMemberName;
	private JLabel lblMemberCourse;
	private JLabel lblMemberBorrowed;
	private JLabel lblMemberReserving;
	private JLabel lblMemberStatus;
	
	public AdminOperationPanel() {
		setLayout(null);
		
		JPanel userInfoPanel = new JPanel();
		userInfoPanel.setBounds(20, 49, 228, 310);
		userInfoPanel.setBorder(BorderFactory.createEtchedBorder());
		add(userInfoPanel);
		userInfoPanel.setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblName.setBounds(10, 11, 77, 28);
		userInfoPanel.add(lblName);
		
		JLabel lblCourse = new JLabel("Course:");
		lblCourse.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblCourse.setBounds(10, 63, 77, 28);
		userInfoPanel.add(lblCourse);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 121, 208, 2);
		userInfoPanel.add(separator);
		
		JLabel lblBorrowed = new JLabel("Borrowed:");
		lblBorrowed.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblBorrowed.setBounds(10, 153, 77, 28);
		userInfoPanel.add(lblBorrowed);
		
		JLabel lblReserving = new JLabel("Reserving:");
		lblReserving.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblReserving.setBounds(10, 205, 77, 28);
		userInfoPanel.add(lblReserving);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblStatus.setBounds(10, 259, 77, 28);
		userInfoPanel.add(lblStatus);
		
		lblMemberName = new JLabel("");
		lblMemberName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblMemberName.setBounds(79, 19, 139, 14);
		userInfoPanel.add(lblMemberName);
		
		lblMemberCourse = new JLabel("");
		lblMemberCourse.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblMemberCourse.setBounds(79, 71, 139, 14);
		userInfoPanel.add(lblMemberCourse);
		
		lblMemberBorrowed = new JLabel("");
		lblMemberBorrowed.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblMemberBorrowed.setBounds(79, 161, 139, 14);
		userInfoPanel.add(lblMemberBorrowed);
		
		lblMemberReserving = new JLabel("");
		lblMemberReserving.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblMemberReserving.setBounds(79, 213, 139, 14);
		userInfoPanel.add(lblMemberReserving);
		
		lblMemberStatus = new JLabel("");
		lblMemberStatus.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblMemberStatus.setBounds(79, 267, 139, 14);
		userInfoPanel.add(lblMemberStatus);
		
		entityPanelContainer = new EntityPanelContainer();
		entityPanelContainer.setBounds(258, 11, 531, 446);
		add(entityPanelContainer);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getAdminPanel().changePanel("adminNaviPanel");
			}
		});
		btnBack.setBounds(72, 379, 89, 23);
		add(btnBack);
	}
	
	public void setMember(Member member) throws Exception {
		this.member = member;
		lblMemberName.setText(member.getName());
		lblMemberCourse.setText(member.getCourse());
		lblMemberBorrowed.setText(((Integer) member.getBorrowed()).toString());
		lblMemberReserving.setText(((Integer) member.getReserving()).toString());
		if (member.isOverdue()) {
			lblMemberStatus.setText("Overdued");
			lblMemberStatus.setForeground(Color.RED);
		} else {
			lblMemberStatus.setText("Normal");
			lblMemberStatus.setForeground(Color.BLACK);
		}
	}
	
	public EntityPanelContainer getEntityPanelContainer() {
		return entityPanelContainer;
	}
	
	public void init() {
		frame = LmsFrame.GetLmsFrame(this);
	}
}
