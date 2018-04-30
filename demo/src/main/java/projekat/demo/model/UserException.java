package projekat.demo.model;

public class UserException {
	private User user;
	private String message;

	public UserException() {
	}

	public UserException(User user, String message) {
		this();
		this.user = user;
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
