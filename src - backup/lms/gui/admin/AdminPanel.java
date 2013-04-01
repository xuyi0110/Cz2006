package lms.gui.admin;

import java.awt.CardLayout;

import javax.swing.JPanel;


public class AdminPanel extends JPanel{
	AdminNaviPanel adminNaviPanel;
	AdminManagementPanel adminManagementPanel;
	
	public AdminPanel() {
		setLayout(new CardLayout());
		
		adminNaviPanel = new AdminNaviPanel();
		add(adminNaviPanel, "adminNaviPanel");
		
		adminManagementPanel = new AdminManagementPanel();
		add(adminManagementPanel, "adminManagementPanel");
	}
	
	public void changePanel(String panel) {
		CardLayout cardLayout = (CardLayout)(getLayout());
		cardLayout.show(this, panel);
	}
	
	public void init() {
		adminNaviPanel.init();
		adminManagementPanel.init();
	}
	
	public AdminManagementPanel getAdminManagementPanel() {
		return adminManagementPanel;
	}

}
