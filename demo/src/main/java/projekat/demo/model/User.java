package projekat.demo.model;

import java.io.Serializable;
import static javax.persistence.InheritanceType.SINGLE_TABLE;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;

import projekat.demo.validator.EmailAnnotation;
import projekat.demo.validator.PasswordAnnotation;

@Entity
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class User implements Serializable {

	private static final long serialVersionUID = -2230328594830389946L;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String surname;

	@Column(nullable = false)
	@Id
	@EmailAnnotation
	private String email;

	@PasswordAnnotation
	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private String phone;

	@Column(nullable = false)
	private RoleType role;

	public User() {
	}

	public User(String name, String surname, String email, String password, String address, String phone,
			RoleType role) {
		this();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phone = phone;
		this.role = role;

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

	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
