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
	private Address address;
	
	@Column(nullable=false)
	private String phone;
	
	@Column(nullable=false)
	@Enumerated(EnumType.ORDINAL)
	private RoleType type;

	public User() {
	}

	public User(String name, String surname, String email, String password, Address address, String phone,
			RoleType type) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phone = phone;
		this.type = type;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
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

}
