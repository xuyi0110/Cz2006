package lms.gui.admin;

import javax.swing.JOptionPane;

import lms.Config;
import lms.manager.ItemMgr;

public class DeleteItemSearchPanel extends EntitySearchPanel{
	public DeleteItemSearchPanel() {
		setSearchInfo("Enter ISBN: ");
	}

	@Override
	public void accept() throws Exception {
		ItemMgr itemMgr = frame.getItemMgr();
		String isbn = textField.getText();
		AdminManagementPanel adminManagementPanel = frame.getAdminPanel().getAdminManagementPanel();
		if (!itemMgr.checkItemExist(isbn)) {
			JOptionPane.showMessageDialog(null, "item not exist", Config.TITLE_WARNING, JOptionPane.ERROR_MESSAGE);
			return;
		}
		adminManagementPanel.getAdminItemDeletePanel().setItem(itemMgr.getItem(isbn));
		adminManagementPanel.changePanel("adminItemDeletePanel");
		
	}
}
