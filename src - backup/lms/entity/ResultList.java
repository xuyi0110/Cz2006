package lms.entity;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ResultList extends Entity{
	
	private String keyword;
	
	private ArrayList<String> searchArea;
	
	private ArrayList<Item> items; 
	
	public ResultList(){

	}
	
	public ResultList(String keyword){
		this.keyword = keyword;
		searchArea = new ArrayList<String>();
		items = new ArrayList<Item>();
	}
	public ResultList(String keyword, ArrayList<String> searchArea){
		this.keyword = keyword;
		this.searchArea = searchArea;
		items = new ArrayList<Item>();
	}

	@Override
	public boolean getInfo() throws Exception {
		DBconnect();
		String query = "select * from item where false";
		for (String area : searchArea) {
			query += " or " + area + " like \"%"+keyword+"%\"";
		}
		query += ";";
		ResultSet rs = st.executeQuery(query);
		
		boolean found = false;
		while(rs.next()){
			Item item = new Item(rs.getString("ISBN"));
			items.add(item);
			found = true;
		}
		st.close();
		return found;
	}

	public ArrayList<Item> getItems() {
		return items;
	}
	@Override
	public void updateIntoDataBase() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
