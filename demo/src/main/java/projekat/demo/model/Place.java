package projekat.demo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Place {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private PlaceType type;
	
	@JsonBackReference
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="place")
	private Set<Arena> arenas;

	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL,  mappedBy="place")
	private Set<Projection> projections;
	
	public Place() {
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public PlaceType getType() {
		return type;
	}

	public void setType(PlaceType type) {
		this.type = type;
	}

	public Set<Arena> getArenas() {
		return arenas;
	}

	public void setArenas(Set<Arena> arenas) {
		this.arenas = arenas;
	}

	public Set<Projection> getProjections() {
		return projections;
	}

	public void setProjections(Set<Projection> projections) {
		this.projections = projections;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Place(Integer id, String name, String description, String address, PlaceType type, Set<Arena> arenas,
			Set<Projection> projections) {
		this();
		this.id = id;
		this.name = name;
		this.description = description;
		this.address = address;
		this.type = type;
		this.arenas = arenas;
		this.projections = projections;
	}

	
	
	

}
