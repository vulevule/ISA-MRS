package projekat.demo.model;

import java.util.ArrayList;

import javax.persistence.Column;

public class Visitor extends User {
	private ArrayList<Visitor> friends;
	
	@Column
	private boolean activate;
	
	
	
	public Visitor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Visitor(String name, String surname, String email, String password, Address address, String phone,
			RoleType type, ArrayList<Visitor> friends, boolean activate) {
		super(name, surname, email, password, address, phone, type);
		// TODO Auto-generated constructor stub
		this.activate = activate;
		this.friends = friends;
		
	}
	public ArrayList<Visitor> getFriends() {
		return friends;
	}
	public void setFriends(ArrayList<Visitor> friends) {
		this.friends = friends;
	}
	public boolean isActivate() {
		return activate;
	}
	public void setActivate(boolean activate) {
		this.activate = activate;
	}
	
	
}
