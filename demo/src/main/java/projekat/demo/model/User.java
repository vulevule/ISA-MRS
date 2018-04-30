package projekat.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import projekat.demo.validator.EmailAnnotation;
import projekat.demo.validator.PasswordAnnotation;

@Entity

public class User  implements Serializable{
	
	private static final long serialVersionUID = -2230328594830389946L;

	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String surname;
	
	@Column(nullable=false)
	@Id
	@EmailAnnotation
	private String email;
	
	@PasswordAnnotation
	@Column(nullable=false)
	private String password;
	
	@Column
	private String address;
	
	@Column(nullable=false)
	private String phone;
	
	@Column(nullable=false)
	@Enumerated(EnumType.ORDINAL)
	private RoleType type;
	
	@Column(nullable=false)
	private boolean activate;
	
	@Column
	private String activateString;

	public User() {
	}

	public User(String name, String surname, String email, String password, String address, String phone,
			RoleType type, boolean activate, String activateString) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phone = phone;
		this.type = type;
		this.activate = activate;
		this.activateString = activateString;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public RoleType getType() {
		return type;
	}

	public void setType(RoleType type) {
		this.type = type;
	}

	public boolean isActivate() {
		return activate;
	}

	public void setActivate(boolean activate) {
		this.activate = activate;
	}

	public String getActivateString() {
		return activateString;
	}

	public void setActivateString(String activateString) {
		this.activateString = activateString;
	}

	
}
