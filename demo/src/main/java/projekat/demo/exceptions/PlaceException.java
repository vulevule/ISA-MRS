package projekat.demo.exceptions;

import projekat.demo.model.Place;

public class PlaceException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	private Place place;
	private String message;

	public PlaceException(String message) {
		super(message);
	}

	public PlaceException(Place place, String message) {
		super(message);
		this.place = place;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
