package projekat.demo.dto;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import projekat.demo.model.PlaceType;

public class PlaceDto implements Serializable {

	private static final long serialVersionUID = 2641408993293592794L;

	private Long id;
	@NotNull
	private String name;
	@NotNull
	private String description;
	@NotNull
	private String address;
	@NotNull
	private PlaceType type;
	@NotEmpty
	private Set<ArenaDto> arenas;
	@NotEmpty
	private Set<ProjectionDto> projections;
	private String userEmail;

	@JsonCreator
	public PlaceDto(@JsonProperty("id") Long id, @JsonProperty("name") String name, @JsonProperty("description") String description,
			@JsonProperty("address") String address, @JsonProperty("type") PlaceType type,
			@JsonProperty("arenas") Set<ArenaDto> arenas, @JsonProperty("projection-place") Set<ProjectionDto> projections,
			@JsonProperty("userEmail") String userEmail) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.address = address;
		this.type = type;
		this.arenas = arenas;
		this.projections = projections;
		this.userEmail = userEmail;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public PlaceType getType() {
		return type;
	}

	public void setType(PlaceType type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<ArenaDto> getArenas() {
		return arenas;
	}

	public void setArenas(Set<ArenaDto> arenas) {
		this.arenas = arenas;
	}

	public Set<ProjectionDto> getProjections() {
		return projections;
	}

	public void setProjections(Set<ProjectionDto> projections) {
		this.projections = projections;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

}
