package projekat.demo.exceptions;

import projekat.demo.model.Friendship;

public class FriendshipException extends RuntimeException {
	
	private Friendship fs;
	private String message;

	public FriendshipException() {
	}

	public FriendshipException(Friendship fs, String message) {
		this();
		this.fs = fs;
		this.message = message;
	}

	public Friendship getFs() {
		return fs;
	}

	public void setFs(Friendship fs) {
		this.fs = fs;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
