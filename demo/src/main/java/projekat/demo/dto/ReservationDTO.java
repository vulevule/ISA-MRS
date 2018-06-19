package projekat.demo.dto;

import java.io.Serializable;
import java.util.Set;

public class ReservationDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int term;
	private Set<String> seats;
	private Set<String> inviteFriends;
	
	public ReservationDTO(){}
	
	public ReservationDTO(int term, Set<String> seats, Set<String> inviteFriends) {
		this();
		this.term = term;
		this.seats = seats;
		this.inviteFriends = inviteFriends;
	}
	

	public int getTerm() {
		return term;
	}
	public void setTerm(int term) {
		this.term = term;
	}
	public Set<String> getSeats() {
		return seats;
	}
	public void setSeats(Set<String> seats) {
		this.seats = seats;
	}
	public Set<String> getInviteFriends() {
		return inviteFriends;
	}
	public void setInviteFriends(Set<String> inviteFriends) {
		this.inviteFriends = inviteFriends;
	}
	
	
	
	
}
