package lms.gui.admin;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import lms.entity.Member;

public class AdminMemberDeletePanel extends AdminEntityUpdatePanel {
	
	private Member member;
	
	private JLabel lblMemberbooksHolding;
	private JLabel lblMemberReserving;
	private JLabel lblMemberFine;
	private JLabel lblMemberStatus;
	
	private JLabel lblName;
	private JLabel lblCourse;
	private JLabel lblMid;
	private JLabel lblYear;
	private JLabel lblType;
	
	public AdminMemberDeletePanel() {
		
		lblName = new JLabel("Name");
		lblName.setFont(new Font("STZhongsong", Font.PLAIN, 15));
		lblName.setBounds(117, 62, 163, 21);
		add(lblName);
		
		lblCourse = new JLabel("Course");
		lblCourse.setFont(new Font("STZhongsong", Font.PLAIN, 15));
		lblCourse.setBounds(117, 139, 163, 21);
		add(lblCourse);
		
		lblMid = new JLabel("MId");
		lblMid.setFont(new Font("STZhongsong", Font.PLAIN, 15));
		lblMid.setBounds(117, 100, 163, 21);
		add(lblMid);
		
		lblYear = new JLabel("Year");
		lblYear.setFont(new Font("STZhongsong", Font.PLAIN, 15));
		lblYear.setBounds(117, 180, 163, 21);
		add(lblYear);
		
		lblType = new JLabel("Type");
		lblType.setFont(new Font("STZhongsong", Font.PLAIN, 15));
		lblType.setBounds(117, 223, 163, 21);
		add(lblType);
		
		JLabel lblBooksHolding = new JLabel("Books Holding:");
		lblBooksHolding.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblBooksHolding.setBounds(361, 63, 134, 21);
		add(lblBooksHolding);
		
		JLabel lblReserving = new JLabel("Reserving:");
		lblReserving.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblReserving.setBounds(361, 100, 134, 21);
		add(lblReserving);
		
		JLabel lblFine = new JLabel("Fine:");
		lblFine.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblFine.setBounds(361, 140, 134, 21);
		add(lblFine);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblStatus.setBounds(361, 181, 134, 21);
		add(lblStatus);
		
		lblMemberbooksHolding = new JLabel("MemberBooksHolding");
		lblMemberbooksHolding.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMemberbooksHolding.setBounds(472, 66, 134, 16);
		add(lblMemberbooksHolding);
		
		lblMemberReserving = new JLabel("MemberReserving");
		lblMemberReserving.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMemberReserving.setBounds(472, 103, 134, 16);
		add(lblMemberReserving);
		
		lblMemberFine = new JLabel("MemberFine");
		lblMemberFine.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMemberFine.setBounds(472, 142, 134, 16);
		add(lblMemberFine);
		
		lblMemberStatus = new JLabel("MemberStatus");
		lblMemberStatus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMemberStatus.setBounds(472, 185, 134, 16);
		add(lblMemberStatus);
	}
	
	public void setMember(Member member) {
		if (member.getBorrowed()>0) {
			btnSubmit.setEnabled(false);
			lblMemberbooksHolding.setForeground(Color.RED);
		} else {
			btnSubmit.setEnabled(true);
			lblMemberbooksHolding.setForeground(Color.BLACK);
		}
			
		this.member = member;
		lblName.setText(member.getName());
		lblCourse.setText(member.getCourse());
		lblYear.setText(((Integer) member.getYear()).toString());
		lblMid.setText(member.getId());
		lblType.setText(member.getType());
		
		lblMemberbooksHolding.setText(((Integer) member.getBorrowed()).toString());
		lblMemberReserving.setText(((Integer) member.getReserving()).toString());
		lblMemberFine.setText(member.getTotalFine());
		if (member.isOverdue()) {
			lblMemberStatus.setText("Overdued");
			lblMemberStatus.setForeground(Color.RED);
		} else {
			lblMemberStatus.setText("Normal");
			lblMemberStatus.setForeground(Color.BLACK);
		}
	}

	@Override
	public void accept() throws Exception {
		frame.getMemberMgr().deleteMember(member);
	}

}
