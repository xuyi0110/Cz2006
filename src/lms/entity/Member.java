package lms.entity;


import java.sql.*;
import java.text.DecimalFormat;
import java.util.Date;

import lms.Config;

public class Member extends User{
	
	private String course;
	private int year;
	private boolean isStudent = false;
	private int quota;
	private int borrowed;
	private int overdue;
	private int loanDuration;
	private int renewLimit;
	private double totalFine = 0;
	private int reserving;

	public Member() {
		super();
		
	}
	
	public Member(String id) {
		super(id);
	}
	
	public Member(String id, String name, String course, int year, boolean isStudent) {
		super(id, name);
		
	}
	
	
	@Override
	public boolean getInfo() throws Exception{
		DBconnect();
		ResultSet rs;
		String query = "select * from member where mID = '" + id +"';";
		
		rs = st.executeQuery(query);
		while(rs.next()){
			this.name = rs.getString("name");
			this.course = rs.getString("course");
			this.year = rs.getInt("year");
			if (rs.getBoolean("isStudent")==true) {
				this.isStudent = true;
				this.quota = Config.STUDENT_QUOTA;
				this.loanDuration = Config.STUDENT_LOAN_DURATION;
				this.renewLimit = Config.STUDENT_RENEW_LIMIT;
			} else {
				this.isStudent = false;
				this.quota = Config.STUFF_QUOTA;
				this.loanDuration = Config.STUFF_LOAN_DURATION;
				this.renewLimit = Config.STUFF_RENEW_LIMIT;
			}
			this.borrowed = 0;
			this.overdue = 0;
			this.totalFine = 0;
			this.reserving = 0;
			
			query = "select * from outstanding_loan where mID = '" + id +"';";
			ResultSet result= st.executeQuery(query);

			while (result.next()) {
				this.borrowed++;
				Date brwDate = Config.DATE_FORMAT.parse(result.getString("date"));
				Loan loan = new Loan(this, new Item(), brwDate);
				if (loan.getFine() > 0) {
					this.overdue ++;
					this.totalFine += loan.getFine(); 
				}
			}
			
			query = "select * from reservation where mID = '" + id +"';";
			result= st.executeQuery(query);
			
			while (result.next()) {
				this.reserving++;
			}
			
			st.close();
			return true;
		}
		if (st != null)
			st.close();
		return false;
	}
	
	public void deleteFromDataBase() throws Exception {
		DBconnect();
		String query = "DELETE FROM member WHERE mID ='"+ id + "';";
		st.execute(query);
		
		query = "DELETE FROM shelf WHERE mID ='"+ id + "';";
		st.execute(query);
		query = "DELETE FROM reservation WHERE mID ='"+ id + "';";
		st.execute(query);
		
		query = "DELETE FROM loan_request WHERE mID ='"+ id + "';";
		st.execute(query);
		st.close();
	}
	
	public void deleteFromAccount() throws Exception {
		DBconnect();
		String query = "DELETE FROM account WHERE mID ='"+ id + "';";
		st.execute(query);
		st.close();
	}
	
	@Override
	public void updateIntoDataBase() throws Exception {
		deleteFromDataBase();
		DBconnect();
		st.executeUpdate("INSERT INTO member "+"VALUES('"+ id + "','" + name + "','" + course + "','" + year +"','" 
							+ (isStudent?1:0 )+ "');");
		st.close();
	}


	

	public int getQuota() {
		return quota;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}

	public boolean isOverdue() {
		return overdue > 0;
	}
	
	public int getOverdue() {
		return overdue;
	}

	public int getBorrowed() {
		return borrowed;
	}

	public int getLoanDuration() {
		return loanDuration;
	}
	
	public int getRenewLimit() {
		return renewLimit;
	}
	
	public String getCourse() {
		return course;
	}
	
	public void setCourse(String course) {
		this.course = course;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public String getTotalFine() {
		 DecimalFormat format = new DecimalFormat("#.##");
	     return format.format(totalFine);
	}
	public String getType() {
		if (isStudent) {
			return "Student";
		}
		else return "Staff";
	}
	
	public void setType(String type) {
		if (type.equals("Student")) 
			isStudent = true;
		else isStudent =false;
	}
	
	public int getReserving() {
		return reserving;
	}
	
	public void updateIntoAccount(String password) throws SQLException {
		DBconnect();
		String query = "DELETE FROM account WHERE mID ='"+ id + "';";
		st.execute(query);
		st.executeUpdate("INSERT INTO account "+"VALUES('"+ id + "','" + password + "');");
		st.close();
		
	}
}
