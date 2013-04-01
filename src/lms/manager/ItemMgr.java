package lms.manager;

import java.sql.SQLException;

import lms.entity.Item;

public class ItemMgr {
	
	public Item getItem(String isbn) throws Exception {
		Item item = new Item(isbn);
		item.getInfo();
		return item;
	}
	
	public boolean checkItemExist(String isbn) throws Exception {
		Item item = new Item(isbn);
		return item.getInfo();
	}
	
	public void addItem(Item item) throws SQLException {
		item.updateIntoDataBase();
	}
	
	public void deleteItem(Item item) throws SQLException {
		item.deleteFromDataBase();
	}

}
