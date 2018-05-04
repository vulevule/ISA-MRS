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
	private int row;

	@Column
	private int column;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="arena")
	private Set<Term> terms;

	@ManyToOne(optional=false)
	private Place place;
	
	public Arena() {
	}

	

}
