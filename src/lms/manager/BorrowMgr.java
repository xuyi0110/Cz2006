package lms.manager;

import java.util.ArrayList;

import lms.entity.Item;
import lms.entity.Loan;
import lms.entity.Member;

public class BorrowMgr {
	
	
	public BorrowMgr(){
		
	}
	
	public ArrayList<Loan> viewAllBorrowedItems(Member borrower) throws Exception {
		Loan loan = new Loan(borrower);
		return loan.getAllLoanedItem();
		
	}
	
	public void borrow(Member borrower, Item brwItem) throws Exception{
		
		if (!borrower.getInfo())
			throw new Exception("borrower not exist");
		
		if(!validate_member(borrower)) {
			throw new Exception("borrower has overdued item or reaches its limit");
		}
		
		if (!brwItem.getInfo()) 
			throw new Exception("Item not exist");
		
		if (!validate_Item(brwItem)) {
			throw new Exception("Item not available");
		}
		
		Loan loan=new Loan(borrower,brwItem);
		loan.updateIntoRequestDataBase();
	}
	

	
	public boolean validate_member(Member borrower){
		if(borrower.getBorrowed()>=borrower.getQuota())
			return false;
		if(borrower.isOverdue())
			return false;
		return true;
	}
	
	public boolean validate_Item(Item brwItem) {
		return brwItem.isAvailable();
	}

}
