package projekat.demo.model;

import java.io.Serializable;
import java.sql.Date;
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
public class Ad implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String description;
	
	@Column(nullable=false)
	private Date expirationDate;
	
	@Column(nullable=false)
	private boolean approved;
	
	@ManyToOne(optional=false)
	private User user;
		
	@ManyToOne(optional=false)
	private Projection projection;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="ad")
	private Set<Bid> bids;
}
