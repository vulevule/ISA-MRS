package projekat.demo.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Term implements Serializable {

	private static final long serialVersionUID = -7156861256132077357L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private Date projectionDate;

	@Column(nullable = false)
	private Time projectionTime;

	@OneToOne
	private Place place;

	@Column(nullable = false)
	private double price;

	@OneToOne
	private Arena arena; // sala u kojoj se odrzava film ili predstava

	@Column(nullable = false)
	private int freeSeats;

	@ManyToOne
	private Projection projection;

	public Term() {
	}

	public Term(Date projectionDate, Time projectionTime, Place place, double price, Arena arena, int freeSeats,
			Projection projection) {
		this();
		this.projectionDate = projectionDate;
		this.projectionTime = projectionTime;
		this.place = place;
		this.price = price;
		this.arena = arena;
		this.freeSeats = freeSeats;
		this.projection = projection;
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

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
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
