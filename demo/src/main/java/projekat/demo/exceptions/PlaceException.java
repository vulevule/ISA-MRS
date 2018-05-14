package projekat.demo.exceptions;

import projekat.demo.model.Place;

public class PlaceException {
	private Place place;
	private String message;

	public PlaceException() {
	}

	public PlaceException(Place place, String message) {
		this();
		this.place = place;
		this.message = message;
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
