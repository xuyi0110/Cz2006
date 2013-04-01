package lms.manager;

import java.util.ArrayList;

import lms.entity.Item;
import lms.entity.ResultList;

public class SearchMgr {
	
	public ArrayList<Item> getSearchResult(String keyword, ArrayList<String> searchArea) throws Exception {
		ResultList resultList = new ResultList(keyword, searchArea);
		resultList.getInfo();
		return resultList.getItems();
	}

}
