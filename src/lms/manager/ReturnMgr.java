package lms.manager;

import java.util.ArrayList;

import lms.entity.*;

public class ReturnMgr {
	
	public boolean returnItem(Loan loan) throws Exception {
		double fine = loan.getFine();
		if (fine > 0) {
			return false;
		}
		confirmReturn(loan);
		return true;
	}
	
	public void confirmPay(Loan loan) throws Exception {
		confirmReturn(loan);
	}
	
	private void confirmReturn(Loan loan) throws Exception {
		loan.updateReturn();
		
		Reservation reservation = new Reservation(loan.getItem());
		if (!reservation.getFirstReservationOfItem())
			return;
		reservation.deleteFromDataBase();
		
		Loan newLoan = new Loan(reservation.getReserver(), reservation.getItem());
		
		newLoan.updateIntoRequestDataBase();
	}
	
	public ArrayList<Loan> getLoanList(Member borrower) throws Exception {
		Loan loan = new Loan(borrower);
		return loan.getAllLoanedItem();
	}
}