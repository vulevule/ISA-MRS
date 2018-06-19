package projekat.demo.dto;

import java.io.Serializable;

import projekat.demo.model.Visitor;

public class FriendUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Visitor v;
	private String status;

	public FriendUser() {
	}

	public FriendUser(Visitor v, String status) {
		this();
		this.v = v;
		this.status = status;
	}

	public Visitor getV() {
		return v;
	}

	public void setV(Visitor v) {
		this.v = v;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
