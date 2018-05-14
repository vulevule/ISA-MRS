package projekat.demo.exceptions;

import projekat.demo.model.Projection;

public class ProjectionException extends RuntimeException {

	private Projection projection;
	private String message;

	public ProjectionException() {
	}

	public ProjectionException(Projection projection, String message) {
		this();
		this.projection = projection;
		this.message = message;
	}

	public Projection getProjection() {
		return projection;
	}

	public void setProjection(Projection projection) {
		this.projection = projection;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
