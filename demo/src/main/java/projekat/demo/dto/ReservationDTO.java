package projekat.demo.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class ReservationDTO implements Serializable{
	private String place;
	private String projection;
	private String term;
	private ArrayList<String> seats;
	private ArrayList<String> inviteFriends;
	
	public ReservationDTO(){}
	
	public ReservationDTO(String place, String projection, String term, ArrayList<String> seats, ArrayList<String> inviteFriends) {
		this();
		this.place = place;
		this.projection = projection;
		this.term = term;
		this.seats = seats;
		this.inviteFriends = inviteFriends;
	}
	
	
	
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getProjection() {
		return projection;
	}
	public void setProjection(String projection) {
		this.projection = projection;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public ArrayList<String> getSeats() {
		return seats;
	}
	public void setSeats(ArrayList<String> seats) {
		this.seats = seats;
	}
	public ArrayList<String> getInviteFriends() {
		return inviteFriends;
	}
	public void setInviteFriends(ArrayList<String> inviteFriends) {
		this.inviteFriends = inviteFriends;
	}
	
	
	
	
}
