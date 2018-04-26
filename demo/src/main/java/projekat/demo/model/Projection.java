package projekat.demo.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Projection implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(nullable=false)
	@Id
	private String name;
	
	@Column(nullable=false)
	@Enumerated(EnumType.ORDINAL)
	private ProjectionType type;
	
	@Column(nullable=false)
	@ManyToMany
	private ArrayList<Place> placesOfProjection;
	
	@Column(nullable=false)
	private ArrayList<String> actors;
	
	@Column(nullable=false)
	private String genre;
	
	@Column(nullable=false)
	private String director;
	
	@Column(nullable=false)
	private int duration;
	
	@Column(nullable=false)
	private String banner;
	
	@Column(nullable=false)
	private double averageRating;
	
	@Column(nullable=false)
	private String description;
	
	@Column(nullable=false)
	private ArrayList<String> rooms;
	
	@Column(nullable=false)
	private ArrayList<String> termsOfProjection;
	
	@Column(nullable=false)
	private double price;
	
	public Projection() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProjectionType getType() {
		return type;
	}

	public void setType(ProjectionType type) {
		this.type = type;
	}

	public ArrayList<String> getActors() {
		return actors;
	}

	public void setActors(ArrayList<String> actors) {
		this.actors = actors;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<String> getRooms() {
		return rooms;
	}

	public void setRooms(ArrayList<String> rooms) {
		this.rooms = rooms;
	}

	public ArrayList<String> getTermsOfProjection() {
		return termsOfProjection;
	}

	public void setTermsOfProjection(ArrayList<String> termsOfProjection) {
		this.termsOfProjection = termsOfProjection;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ArrayList<Place> getPlacesOfProjection() {
		return placesOfProjection;
	}

	public void setPlacesOfProjection(ArrayList<Place> placesOfProjection) {
		this.placesOfProjection = placesOfProjection;
	}

	public Projection(String name, ProjectionType type, ArrayList<Place> placesOfProjection, ArrayList<String> actors,
			String genre, String director, int duration, String banner, double averageRating, String description,
			ArrayList<String> rooms, ArrayList<String> termsOfProjection, double price) {
		super();
		this.name = name;
		this.type = type;
		this.placesOfProjection = placesOfProjection;
		this.actors = actors;
		this.genre = genre;
		this.director = director;
		this.duration = duration;
		this.banner = banner;
		this.averageRating = averageRating;
		this.description = description;
		this.rooms = rooms;
		this.termsOfProjection = termsOfProjection;
		this.price = price;
	}

	
	
	
}
