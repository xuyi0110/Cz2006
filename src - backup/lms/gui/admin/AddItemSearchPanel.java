package lms.gui.admin;

import javax.swing.JOptionPane;

import lms.Config;
import lms.entity.Item;
import lms.manager.ItemMgr;

public class AddItemSearchPanel extends EntitySearchPanel{
	public AddItemSearchPanel() {
		setSearchInfo("Enter ISBN: ");
	}

	@Override
	public void accept() throws Exception {
		ItemMgr itemMgr = frame.getItemMgr();
		String isbn = textField.getText();
		AdminManagementPanel adminManagementPanel = frame.getAdminPanel().getAdminManagementPanel();
		if (itemMgr.checkItemExist(isbn)) {
			String message = "Item already Exist, update?";
			int reply = JOptionPane.showConfirmDialog(null, message, Config.TITLE_WARNING, JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				adminManagementPanel.setPanelName("Update Item");
			}
			else return;
		}
		adminManagementPanel.getAdminItemUpdatePanel().setItem(itemMgr.getItem(isbn));
		adminManagementPanel.changePanel("adminItemUpdatePanel");
	}

}
