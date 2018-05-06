package projekat.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
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

	
	public Friendship(){}
	public Friendship(FriendshipStatus status, User sender, User receiver) {
		this();
		this.status = status;
		this.sender = sender;
		this.receiver = receiver;
	}
	public FriendshipStatus getStatus() {
		return status;
	}
	public void setStatus(FriendshipStatus status) {
		this.status = status;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public User getReceiver() {
		return receiver;
	}
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}	
	
	
	
	
}
