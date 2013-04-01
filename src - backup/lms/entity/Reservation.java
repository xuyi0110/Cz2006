package lms.entity;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import lms.Config;

public class Reservation extends Entity{
	
	private Item item;
	private Member reserver;
	private Date reserveDate = null;
	private int numberInQueue;
	private int queueSize;
	
	public Reservation() {	
	}
	
	public Reservation(Member reserver) {
		this.reserver = reserver;
	}
	
	public Reservation(Item item) {
		this.item = item;
	}
	
	public Reservation(Member reserver, Item item) {
		this.reserver = reserver;
		this.item = item;
		this.reserveDate = new Date();
	}
	
	public Reservation(Member reserver, Item item, Date date) {
		this.reserver = reserver;
		this.item = item;
		this.reserveDate = date;
	}
	
	public Member getReserver() {
		return reserver;
	}
	
	public Item getItem() {
		return item;
	}

	@Override
	public boolean getInfo() throws Exception {
		DBconnect();
		ResultSet rs;
		String query = "select * from reservation where mid = '" + reserver.getId() + "' and isbn = '" + item.getIsbn() + "';";
			
		rs = st.executeQuery(query);
		
		while (rs.next()) {
			this.reserveDate = Config.DATE_FORMAT.parse(rs.getString("date"));
			getQueueInfo();
			st.close();
			return true;
		}
		st.close();
		return false;
	}
	
	public void getQueueInfo() throws Exception {
		if (st == null)
			DBconnect();
		String query = "select * from reservation where isbn = '" + item.getIsbn() + "';";
		ResultSet result = st.executeQuery(query);
		this.numberInQueue = 1;
		this.queueSize = 0;
		while (result.next()) {
			this.queueSize ++;
			if (this.reserveDate == null) continue;
			Date date = Config.DATE_FORMAT.parse(result.getString("date"));
			if (date.getTime() < this.reserveDate.getTime()){
				this.numberInQueue ++;
			}
		}
		
	}
	
	public ArrayList<Reservation> getAllReservations() throws Exception {
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		DBconnect();
		ResultSet rs;
		String query = "select * from reservation where mID = '" + reserver.getId() + "';";
			
		rs = st.executeQuery(query);
		
		while (rs.next()) {
			Reservation reservation = new Reservation(reserver, new Item(rs.getString("isbn")), Config.DATE_FORMAT.parse(rs.getString("date")));
			reservations.add(reservation);
		}
		
		st.close();
		return reservations;	
	}
	
	
	public void deleteFromDataBase() throws Exception {
		DBconnect();
		String query = "DELETE FROM reservation WHERE mid ='"+ reserver.getId() + "' And isbn = '" + item.getIsbn() +"';";
		st.execute(query);
		st.close();
	}

	@Override
	public void updateIntoDataBase() throws Exception {
		deleteFromDataBase();
		DBconnect();
		st.executeUpdate("INSERT INTO reservation "
					+ "(isbn, mID, date)"+"VALUES('" + item.getIsbn() + "','" + reserver.getId() + "','" + Config.DATE_FORMAT.format(reserveDate) +"')");
		st.close();
	}
	
	public boolean getFirstReservationOfItem() throws Exception {
		DBconnect();
		String query = "select * from reservation where isbn = '" + item.getIsbn() + "';";
		ResultSet rs = st.executeQuery(query);
		
		while (rs.next()) {
			Member reserver = new Member(rs.getString("mid"));
			reserver.getInfo();
			if (reserver.getBorrowed() >= reserver.getQuota() || reserver.isOverdue())
				continue;
			if (this.reserveDate == null) {
				this.reserver = reserver;
				this.reserveDate = Config.DATE_FORMAT.parse(rs.getString("date"));
			}
			else {
				Date newDate = Config.DATE_FORMAT.parse(rs.getString("date"));
				if (newDate.getTime() < this.reserveDate.getTime()) {
					this.reserveDate = newDate;
					this.reserver = reserver;
				}
			}
		}
		st.close();
		return (this.reserveDate != null);
	}
	
	public int getNumberInQueue() {
		return numberInQueue;
	}
	
	public int getQueueSize() {
		return queueSize;
	}
	
	public Date getReservedDate() {
		return reserveDate;
	}
}
