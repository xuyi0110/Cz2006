package lms.gui.admin;

import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;


public class AdminManagementPanel extends JPanel {
	private JLabel lblName;
	private JPanel cards;
	
	private AdminItemUpdatePanel adminItemUpdatePanel;
	private AddItemSearchPanel addItemSearchPanel;
	private UpdateItemSearchPanel updateItemSearchPanel;
	
	private DeleteItemSearchPanel deleteItemSearchPanel;
	private AdminItemDeletePanel adminItemDeletePanel;
	
	private AddMemberSearchPanel addMemberSearchPanel;
	private AdminMemberUpdatePanel adminMemberUpdatePanel; 
	private UpdateMemberSearchPanel updateMemberSearchPanel;
	
	private DeleteMemberSearchPanel deleteMemberSearchPanel;
	private AdminMemberDeletePanel adminMemberDeletePanel;
	
	private CheckOutSearchPanel checkOutSearchPanel;
	private AdminOperationPanel adminOperationPanel;
	
	private ReturnSearchPanel returnSearchPanel; 
	
	public AdminManagementPanel() {
		setLayout(null);
		
		JPanel panelTop = new JPanel();
		panelTop.setBounds(0, 0, 821, 54);
		add(panelTop);
		panelTop.setLayout(null);
		
		lblName = new JLabel("Name");
		lblName.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 24));
		lblName.setBounds(92, 28, 271, 26);
		panelTop.add(lblName);
		
		cards = new JPanel(new CardLayout());
		cards.setBounds(0, 52, 821, 467);
		add(cards);
		
		addItemSearchPanel = new AddItemSearchPanel();
		cards.add(addItemSearchPanel, "addItemSearchPanel");
		
		adminItemUpdatePanel = new AdminItemUpdatePanel();
		cards.add(adminItemUpdatePanel, "adminItemUpdatePanel");
		
		updateItemSearchPanel = new UpdateItemSearchPanel();
		cards.add(updateItemSearchPanel, "updateItemSearchPanel");
		
		deleteItemSearchPanel = new DeleteItemSearchPanel();
		cards.add(deleteItemSearchPanel, "deleteItemSearchPanel");
		
		
		adminItemDeletePanel = new AdminItemDeletePanel();
		cards.add(adminItemDeletePanel, "adminItemDeletePanel");
		
		addMemberSearchPanel = new AddMemberSearchPanel();
		cards.add(addMemberSearchPanel, "addMemberSearchPanel");
		
		adminMemberUpdatePanel = new AdminMemberUpdatePanel();
		cards.add(adminMemberUpdatePanel, "adminMemberUpdatePanel");
	
		updateMemberSearchPanel = new UpdateMemberSearchPanel();
		cards.add(updateMemberSearchPanel, "updateMemberSearchPanel");
		
		deleteMemberSearchPanel = new DeleteMemberSearchPanel();
		cards.add(deleteMemberSearchPanel, "deleteMemberSearchPanel");
		
		adminMemberDeletePanel = new AdminMemberDeletePanel();
		cards.add(adminMemberDeletePanel, "adminMemberDeletePanel");
		
		checkOutSearchPanel = new CheckOutSearchPanel();
		cards.add(checkOutSearchPanel, "checkOutSearchPanel");
		
		adminOperationPanel = new AdminOperationPanel();
		cards.add(adminOperationPanel, "adminOperationPanel");
		
		returnSearchPanel = new ReturnSearchPanel();
		cards.add(returnSearchPanel, "returnSearchPanel");
	}
	
	public AdminItemUpdatePanel getAdminItemUpdatePanel() {
		return adminItemUpdatePanel;
	}
	
	public AdminItemDeletePanel getAdminItemDeletePanel() {
		return adminItemDeletePanel;
	}
	
	public AdminMemberUpdatePanel getAdminMemberUpdatePanel() {
		return adminMemberUpdatePanel;
	}
	
	public AdminMemberDeletePanel getAdminMemberDeletePanel() {
		return adminMemberDeletePanel;
	}
	
	public AdminOperationPanel getAdminOperationPanel() {
		return adminOperationPanel;
	}
	
	public CheckOutSearchPanel getCheckOutSearchPanel() {
		return checkOutSearchPanel;
	}
	
	public void setPanelName(String str) {
		lblName.setText("<html><u>" + str + "</u></html>");
	}
	
	public void changePanel(String panel) {
		CardLayout cardLayout = (CardLayout)(cards.getLayout());
		cardLayout.show(cards, panel);
	}
	
	public void init() {
		addItemSearchPanel.init();
		adminItemUpdatePanel.init();
		updateItemSearchPanel.init();
		adminItemDeletePanel.init();
		deleteItemSearchPanel.init();
		addMemberSearchPanel.init();
		adminMemberUpdatePanel.init();
		updateMemberSearchPanel.init();
		deleteMemberSearchPanel.init();
		adminMemberDeletePanel.init();
		checkOutSearchPanel.init();
		adminOperationPanel.init();
		returnSearchPanel.init();
	}

	
}
