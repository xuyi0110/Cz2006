package lms.gui.search;

import javax.swing.JPanel;
import java.awt.CardLayout;



public class MainSearchPanel extends JPanel {
	private SearchItemContainer searchItemContainer;
	private ItemDetailPanel itemDetailPanel;
	
	public MainSearchPanel() {
		setLayout(new CardLayout(0, 0));
		
		searchItemContainer = new SearchItemContainer();
		add(searchItemContainer, "searchItemContainer");
		
		itemDetailPanel = new ItemDetailPanel();
		add(itemDetailPanel, "ItemDetailPanel");
		
	}
	
	public void init() {
		//searchItemContainer.init();
		itemDetailPanel.init();
	}
	
	public void ChangePanel(String panel) {
		CardLayout cardLayout = (CardLayout)(this.getLayout());
		cardLayout.show(this, panel);
		
	}
	
	public SearchItemContainer getSearchItemContainer() {
		return searchItemContainer;
	}
	
	public ItemDetailPanel getItemDetailPanel() {
		return itemDetailPanel;
	}

}
