package projekat.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Friendship {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private FriendshipStatus status;

	@Column(nullable = false)
	private String sender;
	
	@Column(nullable = false)
	private String receiver;

	
	public Friendship(){}
	
	
	
	public Friendship(FriendshipStatus status, String sender, String receiver) {
		this();
		this.status = status;
		this.sender = sender;
		this.receiver = receiver;
	}



	public Friendship(Long id, FriendshipStatus status, String sender, String receiver) {
		this();
		this.id = id;
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
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	
	
	
	
}
