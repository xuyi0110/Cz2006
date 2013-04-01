package lms.gui.member;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import lms.entity.Member;
import lms.gui.LmsFrame;

import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Color;

public class ProfilePanel  extends JPanel {
	private JLabel lblMemberName;
	private JLabel lblMemberCourse;
	private JLabel lblMemberYear;
	
	private LmsFrame frame;
	public ProfilePanel() {
		setBackground(SystemColor.controlHighlight);
		setLayout(null);
		
		JLabel lblInfo = new JLabel("Personal Info");
		lblInfo.setForeground(SystemColor.activeCaption);
		lblInfo.setFont(new Font("Vivaldi", Font.PLAIN, 20));
		lblInfo.setBounds(10, 28, 148, 32);
		add(lblInfo);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(22, 103, 66, 16);
		lblName.setFont(new Font("Traditional Arabic", Font.BOLD, 15));
		add(lblName);
		
		lblMemberName = new JLabel("");
		lblMemberName.setHorizontalAlignment(SwingConstants.LEFT);
		lblMemberName.setBounds(32, 130, 170, 32);
		add(lblMemberName);
		
		JLabel lblCourse = new JLabel("Course:");
		lblCourse.setFont(new Font("Traditional Arabic", Font.BOLD, 15));
		lblCourse.setBounds(22, 173, 66, 16);
		add(lblCourse);
		
		lblMemberCourse = new JLabel("");
		lblMemberCourse.setHorizontalAlignment(SwingConstants.LEFT);
		lblMemberCourse.setBounds(32, 200, 170, 32);
		add(lblMemberCourse);
		
		JLabel lblYear = new JLabel("Year:");
		lblYear.setFont(new Font("Traditional Arabic", Font.BOLD, 15));
		lblYear.setBounds(22, 243, 66, 16);
		add(lblYear);
		
		lblMemberYear = new JLabel("");
		lblMemberYear.setHorizontalAlignment(SwingConstants.LEFT);
		lblMemberYear.setBounds(32, 270, 84, 32);
		add(lblMemberYear);
	}
	
	public void init() {
		frame = (LmsFrame) LmsFrame.GetLmsFrame(this);
	}
	
	public void updateProfile() {
		Member member  =  (Member) frame.getLoginMgr().getUser();
		lblMemberName.setText(member.getName());
		lblMemberCourse.setText(member.getCourse());
		lblMemberYear.setText(((Integer) member.getYear()).toString());
	}
}
