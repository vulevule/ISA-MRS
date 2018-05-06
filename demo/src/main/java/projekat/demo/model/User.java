package projekat.demo.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import projekat.demo.validator.EmailAnnotation;
import projekat.demo.validator.PasswordAnnotation;

@Entity

public class User implements Serializable {

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

	@Column
	private String address;

	@Column(nullable = false)
	private String phone;

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private RoleType type;

	@Column(nullable = false)
	private boolean activate;

	@Column
	private String activateString;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
	private Set<Bid> bids;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
	private Set<Ad> ads;
	
	
	private String repeatPassword;
	
	
	public User() {
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

	public Set<Bid> getBids() {
		return bids;
	}

	public void setBids(Set<Bid> bids) {
		this.bids = bids;
	}

	public Set<Ad> getAds() {
		return ads;
	}

	public void setAds(Set<Ad> ads) {
		this.ads = ads;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public User(String name, String surname, String email, String password, String address, String phone, RoleType type,
			boolean activate, String activateString, Set<Bid> bids, Set<Ad> ads) {
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
		this.bids = bids;
		this.ads = ads;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	
	

}
