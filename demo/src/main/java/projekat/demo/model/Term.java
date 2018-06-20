package projekat.demo.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Term implements Serializable {

	private static final long serialVersionUID = -7156861256132077357L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private LocalDate projectionDate;

	@Column(nullable = false)
	private LocalTime projectionTime;

	@Column(nullable = false)
	private double price;

	@JsonBackReference
	@ManyToOne(optional = false)
	private Arena arena; // sala u kojoj se odrzava film ili predstava

	@Column(nullable = false)
	private int freeSeats;

	@JsonManagedReference
	@ManyToOne
	private Projection projection;

	public Term() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getProjectionDate() {
		return projectionDate;
	}

	public void setProjectionDate(LocalDate projectionDate) {
		this.projectionDate = projectionDate;
	}

	public LocalTime getProjectionTime() {
		return projectionTime;
	}

	public void setProjectionTime(LocalTime projectionTime) {
		this.projectionTime = projectionTime;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Arena getArena() {
		return arena;
	}

	public void setArena(Arena arena) {
		this.arena = arena;
	}

	public int getFreeSeats() {
		return freeSeats;
	}

	public void setFreeSeats(int freeSeats) {
		this.freeSeats = freeSeats;
	}

	public Projection getProjection() {
		return projection;
	}

	public void setProjection(Projection projection) {
		this.projection = projection;
	}

}