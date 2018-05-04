package projekat.demo.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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

	@ManyToMany
	private Set<Place> placesOfProjection;

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

	@OneToMany(mappedBy = "projection")
	private Set<Term> term;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="projection")
	private Set<Ad> ads;

}
