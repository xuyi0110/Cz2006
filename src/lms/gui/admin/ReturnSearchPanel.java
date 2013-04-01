package lms.gui.admin;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import lms.Config;
import lms.entity.Member;
import lms.gui.member.EntityPanelContainer;
import lms.manager.MemberMgr;

public class ReturnSearchPanel extends EntitySearchPanel {
	public ReturnSearchPanel() {
		setSearchInfo("Enter Member Id: ");
	}

	@Override
	public void accept() throws Exception {
		MemberMgr memberMgr = frame.getMemberMgr();
		String mid = textField.getText();
		AdminManagementPanel adminManagementPanel = frame.getAdminPanel().getAdminManagementPanel();
		if (!memberMgr.checkMemberExist(mid)) {
			JOptionPane.showMessageDialog(null, "Member not exist", Config.TITLE_WARNING, JOptionPane.ERROR_MESSAGE);
			return;
		}
		Member member = memberMgr.getMember(mid);
		AdminOperationPanel adminOperationPanel = adminManagementPanel.getAdminOperationPanel();
		adminOperationPanel.setMember(member);
		EntityPanelContainer entityPanelContainer = adminOperationPanel.getEntityPanelContainer();
		entityPanelContainer.setEntityPanelSize(525, 110);
		entityPanelContainer.setPanels(frame.getReturnMgr().getLoanList(member), LoanReturnPanel.class);
		entityPanelContainer.setPanelName("Loans");
		adminManagementPanel.changePanel("adminOperationPanel");
		
	}
	
	
	
}
