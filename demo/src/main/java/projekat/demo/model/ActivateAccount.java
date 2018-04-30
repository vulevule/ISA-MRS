package projekat.demo.model;

import java.io.Serializable;

public class ActivateAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4933264589620709632L;
	private String email;
	private String activateAccount;

	public ActivateAccount() {
	}

	public ActivateAccount(String email, String activateAccount) {
		this();
		this.email = email;
		this.activateAccount = activateAccount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getActivateAccount() {
		return activateAccount;
	}

	public void setActivateAccount(String activateAccount) {
		this.activateAccount = activateAccount;
	}

}
