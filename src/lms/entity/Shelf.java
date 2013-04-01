package lms.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Shelf extends Entity{
	
	Member member;
	
	ArrayList<Item> items;
	
	public Shelf () {
		super();
		items = new	ArrayList<Item>();
	}
	
	public Shelf (Member member) {
		this();
		this.member = member;
	}
	
	public void add(Item item) {
		items.add(item);
	}
	
	public void add(String str) {
		if (str == null || str == "")
			return;
		String[] isbns = str.split(",");
		for (String isbn : isbns) {
			Item item = new Item(isbn);
			add(item);
		}
	}
	
	public void remove(Item item) {
		for (Item shelfItem : items) {
			if (shelfItem.getIsbn().equals(item.getIsbn())) {
				items.remove(shelfItem);
				break;
			}
		}
		
	}

	@Override
	public boolean getInfo() throws Exception {
		DBconnect();
		ResultSet rs;
		String query = "select * from shelf where mid = '" + member.getId() + "';";
		
		rs = st.executeQuery(query);
		
		while (rs.next()) {
			add(rs.getString("items"));
			st.close();
			return true;
		}	
		st.close();
		return false;
	}
	
	public void removeFromDataBase() throws SQLException {
		DBconnect();
		String query = "DELETE FROM shelf WHERE mID ='"+ member.getId() + "';";
		st.execute(query);
	}

	@Override
	public void updateIntoDataBase() throws Exception {
		removeFromDataBase();
		DBconnect();
		String query = "INSERT INTO shelf "+"VALUES('"+ member.getId() + "','" + getItemsInString() + "');";
		st.executeUpdate(query);
		st.close();
	}
	
	public String getItemsInString() {
		String ans = "";
		for (Item item : items) {
			if (ans.equals("")) {
				ans += item.getIsbn();
			} else {
				ans += ("," + item.getIsbn());
			}
		}
		return ans;
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}
	
	public boolean contain(Item item) {
		String isbn = item.getIsbn();
		for (Item shelfItem : items) {
			if (shelfItem.getIsbn().equals(isbn))
			return true;
		}
		return false;
	}
}
