package projekat.demo.model;

import javax.persistence.Entity;

@Entity
public class Visitor extends User {
	
	private boolean activate;
	
	
	
	public Visitor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Visitor(String name, String surname, String email, String password, String address, String phone,
			RoleType type, boolean activate) {
		super(name, surname, email, password, address, phone, type);
		// TODO Auto-generated constructor stub
		this.activate = activate;
		
		
	}
	public boolean isActivate() {
		return activate;
	}
	public void setActivate(boolean activate) {
		this.activate = activate;
	}
	
	
}
