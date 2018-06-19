package projekat.demo.exceptions;

import java.util.ArrayList;

import projekat.demo.model.Reservation;

public class ReservationException {

	private ArrayList<Reservation> r;
	private String message;

	public ReservationException() {
	}

	public ReservationException(ArrayList<Reservation> r, String message) {
		this();
		this.r = r;
		this.message = message;
	}

	public ArrayList<Reservation> getR() {
		return r;
	}

	public void setR(ArrayList<Reservation> r) {
		this.r = r;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
