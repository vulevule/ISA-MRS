package projekat.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Reservation implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8754196883089530286L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@ManyToOne(optional=false)
	private User user;
	
	@ManyToOne(optional=false)
	private Term term;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}

	public Reservation(User user, Term term) {
		super();
		this.user = user;
		this.term = term;
	}
	
	public Reservation() {}
}
