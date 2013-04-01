package lms.manager;

import java.util.ArrayList;

import lms.entity.Item;
import lms.entity.Member;
import lms.entity.Reservation;

public class ReserveMgr {
	
	public void reserve(Member reserver, Item item) throws Exception {
		Reservation reservation = new Reservation(reserver, item);
		reservation.updateIntoDataBase();
	}
	
	public void cancelReservation(Reservation reservation) throws Exception {
		reservation.deleteFromDataBase();
	}
	
	public ArrayList<Reservation> viewAllReservations(Member reserver) throws Exception {
		Reservation reservation = new Reservation(reserver);
		return reservation.getAllReservations();
	}
	
	public boolean checkReserved(Member reserver, Item item) throws Exception {
		Reservation reservation = new Reservation(reserver, item);
		return reservation.getInfo();
		
	}

}
