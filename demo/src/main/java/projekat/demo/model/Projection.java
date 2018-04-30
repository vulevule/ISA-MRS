package projekat.demo.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Projection implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	@Id
	private String name;

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private ProjectionType type;

	/*
	 * @Column(nullable=false)
	 * 
	 * @ManyToMany private Set<Place> placesOfProjection;
	 */

	@Column(nullable = false)
	private String cast;

	@Column(nullable = false)
	private String genre;

	@Column(nullable = false)
	private String director;

	@Column(nullable = false)
	private int duration;

	@Column(nullable = false)
	private String banner;

	@Column(nullable = false)
	private double averageRating;

	@Column(nullable = false)
	private String description;

	@OneToMany(mappedBy = "projection")
	private Set<Term> term;

	public Projection() {
	}

	public Projection(String name, ProjectionType type, Set<Place> placesOfProjection, String cast, String genre,
			String director, int duration, String banner, double averageRating, String description, Set<Term> term) {
		this();
		this.name = name;
		this.type = type;
		// this.placesOfProjection = placesOfProjection;
		this.cast = cast;
		this.genre = genre;
		this.director = director;
		this.duration = duration;
		this.banner = banner;
		this.averageRating = averageRating;
		this.description = description;
		this.term = term;
	}

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

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public Set<Term> getTerm() {
		return term;
	}

	public void setTerm(Set<Term> term) {
		this.term = term;
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

	/*
	 * public Set<Place> getPlacesOfProjection() { return placesOfProjection; }
	 * 
	 * public void setPlacesOfProjection(Set<Place> placesOfProjection) {
	 * this.placesOfProjection = placesOfProjection; }
	 */

}
