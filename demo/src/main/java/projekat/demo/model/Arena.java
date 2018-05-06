package projekat.demo.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Arena implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6739132830220311437L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String name;

	@Column
	private int rowSeats;

	@Column
	private int columnSeats;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="arena")
	private Set<Term> terms;

	@ManyToOne(optional=false)
	private Place place;
	
	public Arena() {
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

	public int getRow() {
		return rowSeats;
	}

	public void setRow(int row) {
		this.rowSeats = row;
	}

	public int getColumn() {
		return columnSeats;
	}

	public void setColumn(int column) {
		this.columnSeats = column;
	}

	public Set<Term> getTerms() {
		return terms;
	}

	public void setTerms(Set<Term> terms) {
		this.terms = terms;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
