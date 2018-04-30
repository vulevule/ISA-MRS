package projekat.demo.model;

import java.io.Serializable;

public class LoginUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7156252099034232288L;
	private String username;
	private String password;

	public LoginUser() {
	}

	public LoginUser(String username, String password) {
		this();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
