package lms.gui.admin;

import javax.swing.JOptionPane;

import lms.Config;
import lms.manager.MemberMgr;

public class UpdateMemberSearchPanel extends EntitySearchPanel{
	public UpdateMemberSearchPanel() {
		setSearchInfo("Enter Member Id: ");
	}

	@Override
	public void accept() throws Exception {
		MemberMgr memberMgr = frame.getMemberMgr();
		String mid = textField.getText();
		AdminManagementPanel adminManagementPanel = frame.getAdminPanel().getAdminManagementPanel();
		if (!memberMgr.checkMemberExist(mid)) {
			String message = "Member not Exist, create?";
			int reply = JOptionPane.showConfirmDialog(null, message, Config.TITLE_WARNING, JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				adminManagementPanel.setPanelName("Add Member");
			}
			else return;
		}
		adminManagementPanel.getAdminMemberUpdatePanel().setMember(memberMgr.getMember(mid));
		adminManagementPanel.changePanel("adminMemberUpdatePanel");
		
	}

}
