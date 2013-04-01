package lms.gui.admin;

import javax.swing.JOptionPane;

import lms.Config;
import lms.entity.Member;
import lms.gui.member.EntityPanelContainer;
import lms.manager.MemberMgr;

public class CheckOutSearchPanel extends EntitySearchPanel{
	EntityPanelContainer entityPanelContainer;
	
	public CheckOutSearchPanel() {
		entityPanelContainer = new EntityPanelContainer();
		entityPanelContainer.setScrollPaneSize(646, 180);
		entityPanelContainer.setBounds(149, 75, 605, 231);
		add(entityPanelContainer);
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
		entityPanelContainer.setPanels(frame.getCheckOutMgr().getRequestList(member), LoanRequestPanel.class);
		entityPanelContainer.setPanelName("Requests");
		adminManagementPanel.changePanel("adminOperationPanel");
	}
	
	public EntityPanelContainer getEntityPanelContainer() {
		return entityPanelContainer;
	}

}
