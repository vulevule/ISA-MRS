package projekat.demo.model;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class Friendship {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private FriendshipStatus status;

	@OneToOne
	private User sender;
	
	@OneToOne
	private User receiver;	
	
}
