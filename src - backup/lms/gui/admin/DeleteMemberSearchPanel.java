package lms.gui.admin;

import javax.swing.JOptionPane;

import lms.Config;
import lms.manager.MemberMgr;

public class DeleteMemberSearchPanel extends EntitySearchPanel{
	public DeleteMemberSearchPanel() {
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
		adminManagementPanel.getAdminMemberDeletePanel().setMember(memberMgr.getMember(mid));
		adminManagementPanel.changePanel("adminMemberDeletePanel");
		
	}
}
