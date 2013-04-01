package lms.manager;

import java.util.ArrayList;

import lms.entity.*;

public class CheckOutMgr {
	
	public void checkOut(Loan loan) throws Exception {
		Member borrower = loan.getBorrower();
		Item item = loan.getItem();
		if (!borrower.getInfo()) 
			throw new Exception("borrower not exist");
		
		
		if (!item.getInfo())
			throw new Exception("item not exist");
		
		if (!item.isAvailable()) {
			throw new Exception("Item currently not available.");
		}
		loan.updateAccept();
	}
	
	public void Reject(Loan loan) throws Exception {
		loan.deleteFromRequst();
	}
	
	public ArrayList<Loan> getRequestList(Member borrower) throws Exception {
		Loan loan = new Loan(borrower);
		return loan.getAllRequesting();
	}
	
	public ArrayList<Loan> getAllRequestList() throws Exception {
		return (new Loan()).getAllRequestLists();
	}
}