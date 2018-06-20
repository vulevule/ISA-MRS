package projekat.demo.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@DiscriminatorValue("Theater_place_admin")
public class PlaceAdmin extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonManagedReference
	@ManyToOne
	private Place place;
	
	
	@Column
	private boolean firstLogin;

	
	public PlaceAdmin(){
		super();
	}
	public PlaceAdmin(String name, String surname, String email, String password, String address, String phone,
			RoleType role, Place place, boolean firstLogin) {
		super(name, surname, email, password, address, phone, role);
		this.place = place;
		this.firstLogin = firstLogin;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public boolean isFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
	
	

}
