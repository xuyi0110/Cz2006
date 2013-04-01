package lms.gui.member;

import java.awt.CardLayout;

import javax.swing.JPanel;

import lms.gui.LmsFrame;

public class UserPanel extends JPanel{
	private LmsFrame frame;
	private ProfilePanel profilePanel;
	private StatusPanel statusPanel;
	private EntityPanelContainer entityPanelContainer;
	
	private JPanel cards;
	public UserPanel() {
		setLayout(null);
		
		profilePanel = new ProfilePanel();
		profilePanel.setBounds(0, 0, 150, 428);
		add(profilePanel);
		
		statusPanel = new StatusPanel();
		
		entityPanelContainer = new EntityPanelContainer();
		
		cards = new JPanel(new CardLayout());
		cards.setBounds(149, 0, 645, 428);
		cards.add(statusPanel, "statusPanel");
		cards.add(entityPanelContainer, "entityPanelContainer");
		add(cards);
	}
	public void init() {
		profilePanel.init();
		statusPanel.init();
		frame = LmsFrame.GetLmsFrame(this);
	}
	
	public void updateProfile() throws Exception {
		if (frame.getLoginMgr().getUser() == null)
			throw new Exception("Please Login first.");
		frame.getLoginMgr().getUser().getInfo();
		profilePanel.updateProfile();
		statusPanel.updateProfile();
	}
	
	public void changePanel(String panel){
		CardLayout cardLayout = (CardLayout)(cards.getLayout());
		cardLayout.show(cards, panel);
	}
	
	public EntityPanelContainer getEntityPanelContainer() {
		return entityPanelContainer;
	}
}
