package projekat.demo.model;

import java.io.Serializable;
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
public class Place implements Serializable {
	
	private static final long serialVersionUID = -3237585465892945619L;

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
	
	@JsonBackReference(value = "arenas")
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="place")
	private Set<Arena> arenas;

	@JsonBackReference(value = "projection-place")
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL,  mappedBy="place")
	private Set<Projection> projections;
	
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
}
