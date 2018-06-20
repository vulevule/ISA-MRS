package projekat.demo.exceptions;

import projekat.demo.model.Reservation;

public class ResException {
	private Reservation r;
	private String message;
	
	public ResException(){}
	public ResException(Reservation r, String message) {
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
