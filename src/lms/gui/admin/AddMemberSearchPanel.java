package lms.gui.admin;

import javax.swing.JOptionPane;

import lms.Config;
import lms.manager.MemberMgr;

public class AddMemberSearchPanel extends EntitySearchPanel {
	public AddMemberSearchPanel() {
		setSearchInfo("Enter Member Id: ");
	}
	
	@Override
	public void accept() throws Exception {
		MemberMgr memberMgr = frame.getMemberMgr();
		String mid = textField.getText();
		AdminManagementPanel adminManagementPanel = frame.getAdminPanel().getAdminManagementPanel();
		if (memberMgr.checkMemberExist(mid)) {
			String message = "Member already Exist, update?";
			int reply = JOptionPane.showConfirmDialog(null, message, Config.TITLE_WARNING, JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				adminManagementPanel.setPanelName("Update Member");
			}
			else return;
		}
		adminManagementPanel.getAdminMemberUpdatePanel().setMember(memberMgr.getMember(mid));
		adminManagementPanel.changePanel("adminMemberUpdatePanel");
	}

}
