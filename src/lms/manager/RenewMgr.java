package lms.manager;

import java.util.ArrayList;

import lms.entity.Item;
import lms.entity.Loan;
import lms.entity.Member;

public class RenewMgr {
	
	public ArrayList<Loan> getLoanList(Member borrower) throws Exception {
		Loan loan = new Loan(borrower);
		return loan.getAllLoanedItem();
	}
	
	public void renew(Loan loan) throws Exception {
		if (loan.getFine() > 0) {
			throw new Exception("Loan is overdued, must return the item first");
		}
		
		Item renewItem = loan.getItem();
		if (renewItem.getFreeQty() < 0) {
			throw new Exception("Item is not available now");
		}
		
		loan.renewLoan();
	}

}
