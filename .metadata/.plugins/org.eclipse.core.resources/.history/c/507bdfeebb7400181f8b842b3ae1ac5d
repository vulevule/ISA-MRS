package projekat.demo.dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TermDto implements Serializable {

	private static final long serialVersionUID = 4949242372989023929L;

	private Long id;
	private Date projectionDate;
	private Time projectionTime;
	private double price;
	private ArenaDto arena;
	private int freeSeats;
	private ProjectionDto projection;

	@JsonCreator
	public TermDto(@JsonProperty("id") Long id, @JsonProperty("projectionDate") Date projectionDate,
			@JsonProperty("projectionTime") Time projectionTime, @JsonProperty("price") double price,
			@JsonProperty("arena") ArenaDto arena, @JsonProperty("freeSeats") int freeSeats,
			@JsonProperty("projection") ProjectionDto projection) {
		this.id = id;
		this.projectionDate = projectionDate;
		this.projectionTime = projectionTime;
		this.price = price;
		this.arena = arena;
		this.freeSeats = freeSeats;
		this.projection = projection;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getProjectionDate() {
		return projectionDate;
	}

	public void setProjectionDate(Date projectionDate) {
		this.projectionDate = projectionDate;
	}

	public Time getProjectionTime() {
		return projectionTime;
	}

	public void setProjectionTime(Time projectionTime) {
		this.projectionTime = projectionTime;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ArenaDto getArena() {
		return arena;
	}

	public void setArena(ArenaDto arena) {
		this.arena = arena;
	}

	public int getFreeSeats() {
		return freeSeats;
	}

	public void setFreeSeats(int freeSeats) {
		this.freeSeats = freeSeats;
	}

	public ProjectionDto getProjection() {
		return projection;
	}

	public void setProjection(ProjectionDto projection) {
		this.projection = projection;
	}

}
