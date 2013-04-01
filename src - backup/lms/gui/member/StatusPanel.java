package lms.gui.member;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

import lms.entity.Member;
import lms.gui.LmsFrame;

import java.awt.Color;

public class StatusPanel extends JPanel{
	private LmsFrame frame;
	
	private JLabel lblMemberStatus;
	private JLabel lblMemberMaxHolding;
	private JLabel lblMemberCurrentHolding;
	private JLabel lblMemberOverdue;
	private JLabel lblMemberFine;
	
	public StatusPanel() {
		setLayout(null);
		
		JLabel lblStatus = new JLabel("<html><u>Status:</u></html>");
		lblStatus.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblStatus.setBounds(136, 44, 127, 37);
		add(lblStatus);
		
		lblMemberStatus = new JLabel("");
		lblMemberStatus.setFont(new Font("Consolas", Font.PLAIN, 18));
		lblMemberStatus.setBounds(321, 44, 132, 37);
		add(lblMemberStatus);
		
		JLabel lblMaxHolding = new JLabel("MaxHolding:");
		lblMaxHolding.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblMaxHolding.setBounds(159, 112, 117, 22);
		add(lblMaxHolding);
		
		JLabel lblCurrentHolding = new JLabel("CurrentHolding:");
		lblCurrentHolding.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCurrentHolding.setBounds(159, 170, 138, 22);
		add(lblCurrentHolding);
		
		JLabel lblOverdue = new JLabel("Overdue:");
		lblOverdue.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblOverdue.setBounds(159, 228, 117, 22);
		add(lblOverdue);
		
		JLabel lblFine = new JLabel("Fine:");
		lblFine.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblFine.setBounds(159, 271, 117, 22);
		add(lblFine);
		
		lblMemberMaxHolding = new JLabel("");
		lblMemberMaxHolding.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMemberMaxHolding.setBounds(321, 120, 117, 14);
		add(lblMemberMaxHolding);
		
		lblMemberCurrentHolding = new JLabel("");
		lblMemberCurrentHolding.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMemberCurrentHolding.setBounds(321, 178, 117, 14);
		add(lblMemberCurrentHolding);
		
		lblMemberOverdue = new JLabel("");
		lblMemberOverdue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMemberOverdue.setBounds(321, 236, 117, 14);
		add(lblMemberOverdue);
		
		lblMemberFine = new JLabel("");
		lblMemberFine.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMemberFine.setBounds(321, 279, 117, 14);
		add(lblMemberFine);
	}
	
	public void init() {
		frame = (LmsFrame) LmsFrame.GetLmsFrame(this);
	}
	
	public void updateProfile() {
		Member member  =  (Member) frame.getLoginMgr().getUser();
		
		if (member.isOverdue()) {
			lblMemberStatus.setText("OVERDUED");
			lblMemberStatus.setFont(new Font("Consolas", Font.BOLD, 18));
			lblMemberStatus.setForeground(Color.RED);
		} else {
			lblMemberStatus.setText("NORMAL");
			lblMemberStatus.setFont(new Font("Consolas", Font.PLAIN, 18));
			lblMemberStatus.setForeground(Color.BLACK);
		}
		
		lblMemberMaxHolding.setText(((Integer) member.getQuota()).toString());
		lblMemberCurrentHolding.setText(((Integer) member.getBorrowed()).toString());
		lblMemberOverdue.setText(((Integer) member.getOverdue()).toString());
		lblMemberFine.setText(member.getTotalFine());
	}
}
