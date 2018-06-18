package projekat.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
public class Reservation implements Serializable {
	
	private static final long serialVersionUID = -8754196883089530286L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@ManyToOne(optional=false)
	private Visitor user;
	
	@ManyToOne(optional=false)
	private Term term;
	
	@Column 
	private int row;
	
	@Column
	private int seatNum;
	
	@Version
	private int version;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Visitor getUser() {
		return user;
	}

	public void setUser(Visitor user) {
		this.user = user;
	}

	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}
	
	

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}

	
	public Reservation() {}

	public Reservation(long id, Visitor user, Term term, int row, int seatNum) {
		this();
		this.id = id;
		this.user = user;
		this.term = term;
		this.row = row;
		this.seatNum = seatNum;
	}

	public Reservation( Visitor user, Term term, int row, int seatNum) {
		this();
	
		this.user = user;
		this.term = term;
		this.row = row;
		this.seatNum = seatNum;
	}
	
	
}
