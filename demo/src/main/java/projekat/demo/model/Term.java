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


	@Column(nullable = false)
	private double price;

	@ManyToOne(optional=false)
	private Arena arena; // sala u kojoj se odrzava film ili predstava

	@Column(nullable = false)
	private int freeSeats;

	@ManyToOne
	private Projection projection;

	public Term() {
	}
}