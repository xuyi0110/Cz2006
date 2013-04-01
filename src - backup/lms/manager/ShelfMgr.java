package lms.manager;

import java.util.ArrayList;

import lms.entity.Item;
import lms.entity.Member;
import lms.entity.Shelf;

public class ShelfMgr {
	
	public ShelfMgr() {
		
	}
	
	public ArrayList<Item> getShelfItems(Member member) throws Exception {
		Shelf shelf = new Shelf(member);
		shelf.getInfo();
		return shelf.getItems();
	}
	
	public void addItemToShelf(Member member, Item item) throws Exception {
		Shelf shelf = new Shelf(member);
		shelf.getInfo();
		shelf.add(item);
		shelf.updateIntoDataBase();
	}
	
	public void removeFromShelf(Member member, Item item) throws Exception {
		Shelf shelf = new Shelf(member);
		shelf.getInfo();
		shelf.remove(item);
		shelf.updateIntoDataBase();
	}
	
	public boolean checkInShelf(Member member, Item item) throws Exception {
		Shelf shelf = new Shelf(member);
		shelf.getInfo();
		return shelf.contain(item);
	}
	

}
