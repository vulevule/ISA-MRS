package projekat.demo.model;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Place {
	@Column(nullable = false)
	@Id
	private String name;
	
	@Column(nullable = false)
	private String description;
	
	@ManyToOne(optional = false)
	private Address address;
	
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private PlaceType type;
	
	@ManyToMany
	private Set<Projection> projections;
	

	public Place() {
	}

	public Place(String name, String description, Address address, PlaceType type, Set<Projection> projections) {
		this();
		this.name = name;
		this.description = description;
		this.address = address;
		this.type = type;
		this.projections = projections;
	}
	
	
	
	public Set<Projection> getProjections() {
		return projections;
	}

	public void setProjections(Set<Projection> projections) {
		this.projections = projections;
	}

	public Place(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public PlaceType getType() {
		return type;
	}

	public void setType(PlaceType type) {
		this.type = type;
	}

}
