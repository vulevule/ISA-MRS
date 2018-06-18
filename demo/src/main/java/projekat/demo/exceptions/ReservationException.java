package projekat.demo.exceptions;

import projekat.demo.model.Reservation;

public class ReservationException {

	private Reservation r;
	private String message;

	public ReservationException() {
	}

	public ReservationException(Reservation r, String message) {
		this();
		this.r = r;
		this.message = message;
	}

	public Reservation getR() {
		return r;
	}

	public void setR(Reservation r) {
		this.r = r;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
