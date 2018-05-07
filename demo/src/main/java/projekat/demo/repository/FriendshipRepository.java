package projekat.demo.repository;

import org.springframework.data.repository.CrudRepository;

import projekat.demo.model.Friendship;
import projekat.demo.model.FriendshipStatus;
import projekat.demo.model.User;

public interface FriendshipRepository extends CrudRepository<Friendship, Long>{
	
	Friendship findBySenderAndReceiverAndStatus(User sender, User receiver, FriendshipStatus status);
	
	Friendship findBySenderAndReceiver(User sender, User receiver);
}
