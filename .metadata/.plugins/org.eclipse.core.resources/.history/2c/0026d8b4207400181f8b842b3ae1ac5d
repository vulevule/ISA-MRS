package projekat.demo.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Projection implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private ProjectionType type;
	
	@JsonManagedReference(value = "projection-place")
	@ManyToOne
	private Place place;

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

	@Column
	private int numOfVisitors;

	@Column(nullable = false)
	private double averageRating;

	@Column(nullable = false)
	private String description;

	@JsonBackReference(value = "terms")
	@OneToMany(mappedBy = "projection")
	private Set<Term> terms;
	
	@JsonBackReference(value = "ads")
	@OneToMany(fetch=FetchType.LAZY, mappedBy="projection")
	private Set<Ad> ads;
	
	@JsonBackReference(value = "thematicProps-projection")
	@OneToMany(fetch=FetchType.LAZY, mappedBy="projection")
	private Set<ThematicProp> thematicProps;

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

	public ProjectionType getType() {
		return type;
	}

	public void setType(ProjectionType type) {
		this.type = type;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
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

	public int getNumOfVisitors() {
		return numOfVisitors;
	}

	public void setNumOfVisitors(int numOfVisitors) {
		this.numOfVisitors = numOfVisitors;
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

	public Set<Term> getTerms() {
		return terms;
	}

	public void setTerms(Set<Term> terms) {
		this.terms = terms;
	}

	public Set<Ad> getAds() {
		return ads;
	}

	public void setAds(Set<Ad> ads) {
		this.ads = ads;
	}
}
