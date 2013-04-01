package lms.entity;

import java.sql.*;

import java.util.ArrayList;
import java.util.Date;

import lms.Config;


public class Loan extends Entity {
	
	private Member borrower;
	private Item brwItem;
	private Date brwDate;
	private int renewedTimes;
	
	public Loan(Member borrower, Item brwItem, Date brwDate, int renewedTimes) {
		super();
		this.borrower = borrower;
		this.brwItem = brwItem;
		this.brwDate = brwDate;
		this.renewedTimes = renewedTimes;
	}
	
	public Loan(Member borrower, Item brwItem, Date brwDate) {
		super();
		this.borrower = borrower;
		this.brwItem = brwItem;
		this.brwDate = brwDate;
	}
	
	public Loan(Member borrower, Item brwItem) {
		super();
		this.borrower = borrower;
		this.brwItem = brwItem;
		this.brwDate = new Date();
		this.renewedTimes = 0;
	}
	
	public Loan(Member borrower) {
		super();
		this.borrower=borrower;
	}
	
	public Loan() {
		super();	
	}
	
	public Member getBorrower() {
		return borrower;
	}
	
	public Item getItem() {
		return brwItem;
	}
	
	public void updateIntoRequestDataBase() throws Exception {
		DBconnect();
		st.executeUpdate("INSERT INTO loan_request (mid, isbn, date) "+"VALUES('"+borrower.getId() + "','" + brwItem.getIsbn()+"','"+ Config.DATE_FORMAT.format(this.brwDate) +"');");
		st.close();
	}
	
	public void deleteFromRequst() throws Exception {
		DBconnect();
		String query = "DELETE FROM loan_request WHERE mid ='"+ borrower.getId() + "' and isbn = '" + brwItem.getIsbn() + "' and date = '" + Config.DATE_FORMAT.format(brwDate) + "';";
		st.execute(query);
		st.close();
	}
	
	public void updateAccept() throws Exception {
		deleteFromRequst();
		DBconnect();
		st.executeUpdate("INSERT INTO outstanding_loan " + "VALUES('" + borrower.getId() + "','" + brwItem.getIsbn() + "','" + Config.DATE_FORMAT.format(brwDate) + "','" + renewedTimes + "');");
		st.close();
	}
	
	public void updateReturn() throws Exception {
		DBconnect();
		String query = "DELETE FROM outstanding_loan WHERE mid ='"+ borrower.getId() + "' and isbn = '" + brwItem.getIsbn() + "' and date = '" + Config.DATE_FORMAT.format(this.brwDate) + "';";
		st.execute(query);
		st.executeUpdate("INSERT INTO loan_history " + "VALUES('" + borrower.getId() + "','" + brwItem.getIsbn() + "','" + Config.DATE_FORMAT.format(this.brwDate) + "','" + renewedTimes + "');");
		st.close();
	}
	
	public ArrayList<Loan> getAllLoanedItem() throws Exception {
		ArrayList<Loan> loans = new ArrayList<Loan>();
		DBconnect();
		ResultSet rs;
		String query = "select * from outstanding_loan where mID = '" + borrower.getId() + "';";
			
		rs = st.executeQuery(query);
		
		while (rs.next()) {
			Loan loan = new Loan(borrower, new Item(rs.getString("isbn")), Config.DATE_FORMAT.parse(rs.getString("date")), rs.getInt("renewed_times"));
			loans.add(loan);
		}
		
		st.close();
		return loans;	
	}
	
	public ArrayList<Loan> getAllBorrower() throws Exception {
		ArrayList<Loan> loans = new ArrayList<Loan>();
		DBconnect();
		ResultSet rs;
		String query = "select * from outstanding_loan where isbn = '" + brwItem.getIsbn() + "';";
			
		rs = st.executeQuery(query);
		
		while (rs.next()) {
			Loan loan = new Loan(new Member(rs.getString("mid")), brwItem, Config.DATE_FORMAT.parse(rs.getString("date")), rs.getInt("renewed_times"));
			loans.add(loan);
		}
		
		st.close();
		return loans;	
	}
	
	public ArrayList<Loan> getAllRequesting() throws Exception {
		ArrayList<Loan> loans = new ArrayList<Loan>();
		DBconnect();
		ResultSet rs;
		String query = "select * from loan_request where mID = '" + borrower.getId() + "';";
			
		rs = st.executeQuery(query);
		
		while (rs.next()) {
			Loan loan = new Loan(borrower, new Item(rs.getString("isbn")));
			loans.add(loan);
		}
		
		st.close();
		return loans;	
	}

	@Override
	public boolean getInfo() throws Exception {
		DBconnect();
		ResultSet rs;
		String query = "select * from loan_request where mid = '" + borrower.getId() + "' and isbn = '" + brwItem.getIsbn() + "';";
			
		rs = st.executeQuery(query);
		
		while (rs.next()) {
			this.brwDate = Config.DATE_FORMAT.parse(rs.getString("date"));
			//this.renewedTimes = rs.getInt("renewed_times");
			st.close();
			return true;
		}
		st.close();
		return false;
	}
	
	public double getFine() {
		Date currentDate = new Date();
		int days = (int) ((currentDate.getTime() - brwDate.getTime()) / (24 * 60 * 60 * 1000));
		if (days >= borrower.getLoanDuration()) {
			return Config.FINE;
		}
		return 0; 
	}
	

	@Override
	public void updateIntoDataBase() throws Exception {
		DBconnect();
		Date currentDate = new Date();
		String query = "UPDATE outstanding_loan SET Date = '" + Config.DATE_FORMAT.format(currentDate) + "', renewed_times = '" + renewedTimes
						+ "' WHERE mID ='"+ borrower.getId() + "' and isbn = '" + brwItem.getIsbn() + "' and Date = '" + Config.DATE_FORMAT.format(this.brwDate) + "';";
		st.executeUpdate(query);
		st.close();	
	}
	
	public boolean renewLoan() throws Exception {
		if (renewedTimes >= borrower.getRenewLimit())
			return false;
		renewedTimes++;
		updateIntoDataBase();
		brwDate = new Date();
		return true;
	}
	
	public ArrayList<Loan> getAllRequestLists() throws SQLException {
		ArrayList<Loan> loans = new ArrayList<Loan>();
		DBconnect();
		ResultSet rs;
		String query = "select * from loan_request;";
			
		rs = st.executeQuery(query);
		
		while (rs.next()) {
			Loan loan = new Loan(new Member(rs.getString("mId")), new Item(rs.getString("isbn")));
			loans.add(loan);
		}
		
		st.close();
		return loans;	
		
	}
	
	public Date getBorrowedDate() {
		return brwDate;
	}
	
	public int getRenewedTimes() {
		return renewedTimes;
	}
}
