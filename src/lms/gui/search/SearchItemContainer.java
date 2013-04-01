package lms.gui.search;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import lms.entity.Item;

public class SearchItemContainer extends JPanel {
	private JPanel itemPanel;
	public SearchItemContainer() {
		setLayout(null);
		itemPanel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane(itemPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 800, 420);
		add(scrollPane);
	}
	
	public void setPanels(ArrayList<Item> items) throws Exception {
		itemPanel.removeAll();
		itemPanel.setPreferredSize(new Dimension(800, ((int)(items.size()+1)/2) * 220));
		itemPanel.setLayout(new GridLayout(((int)(items.size()+1)/2), 2, 0, 0));
		
		for (Item item : items) {
			SearchItemPanel searchItemPanel = new SearchItemPanel();
			searchItemPanel.setEntity(item);
			itemPanel.add(searchItemPanel);
			searchItemPanel.init();
		}
		itemPanel.validate();
		itemPanel.repaint();
		
	}

}
