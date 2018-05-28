package projekat.demo.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import projekat.demo.model.Friendship;
import projekat.demo.model.FriendshipStatus;
import projekat.demo.model.User;

public interface FriendshipRepository extends CrudRepository<Friendship, Long>{
	
	Friendship findBySenderAndReceiverAndStatus(String sender, String receiver, FriendshipStatus status);
	
	Friendship findBySenderAndReceiver(String sender, String receiver);

	Collection<User> findBySenderOrReceiver(String sender, String receiver);

	Collection<Friendship> findBySenderAndStatus(String user, FriendshipStatus approved);

	Collection<Friendship> findByReceiverAndStatus(String user, FriendshipStatus approved);
}
