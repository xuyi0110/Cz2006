package lms.gui.member;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;

import lms.entity.Entity;
import lms.gui.LmsFrame;

import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class EntityPanelContainer extends JPanel{
	
	private int size;
	
	private JPanel panelMain;
	private JPanel panelTop;
	private JLabel lblName;
	private JScrollPane scrollPane;
	
	private int panelWidth;
	private int panelHeight;
	
	private int scrollPaneWidth = 645;
	private int scrollPaneHeight = 353;
	

	
	public EntityPanelContainer() {
		setLayout(null);
		
		panelTop = new JPanel();
		panelTop.setBounds(0, 0, 645, 41);
		add(panelTop);
		panelTop.setLayout(null);
		
		lblName = new JLabel("Name");
		lblName.setBounds(10, 11, 271, 26);
		lblName.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 24));
		panelTop.add(lblName);
		
		panelMain = new JPanel();
		
		//add(panelMain);
		panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
		
		scrollPane = new JScrollPane(panelMain, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		scrollPane.setBounds(0, 40, scrollPaneWidth, scrollPaneHeight);
		scrollPane.setBorder(null);
		add(scrollPane);
		
	}
	
	public void setPanels(ArrayList entities, Class<?> type) throws Exception {
		panelMain.removeAll();
		size = entities.size();
		scrollPane.setBounds(0, 40, panelWidth, Math.min(size * panelHeight, scrollPaneHeight));
		panelMain.setPreferredSize(new Dimension(panelWidth, size * panelHeight));
		for (Object entity : entities) {
			EntityPanel entityPanel = (EntityPanel) type.newInstance();
			panelMain.add(entityPanel);
			entityPanel.init();
			entityPanel.setEntity((Entity) entity);
		}

		panelMain.validate();
		panelMain.repaint(); 
	}
	
	
	public void setPanelName(String str) {
		lblName.setText("<html><u>" + str + "</u></html>");
	}
	
	public void setEntityPanelSize(int width, int height) {
		this.panelWidth = width;
		this.panelHeight = height;
	}
	
	public void setScrollPaneSize(int width, int height) {
		this.scrollPaneWidth = width;
		this.scrollPaneHeight = height;		
	}
	
	public void reducePanelSize() {
		size --;
		scrollPane.setBounds(0, 40, panelWidth, Math.min(size * panelHeight, 353));
		panelMain.setPreferredSize(new Dimension(panelWidth, size * panelHeight));
	}
	
	public static EntityPanelContainer getContainer(Container container) {
		Container parentContainer = container.getParent();
		while (parentContainer != null) {
			if (parentContainer instanceof EntityPanelContainer) 
				return (EntityPanelContainer) parentContainer;
			parentContainer = parentContainer.getParent();
		}
		return (EntityPanelContainer) parentContainer;
	}

}
