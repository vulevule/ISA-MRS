package projekat.demo.service;

import java.util.Collection;

import projekat.demo.model.Friendship;
import projekat.demo.model.User;

public interface UserService {

	User createUser(User u);

	User login(String username, String password);

	User setActivateString(User createUser);

	User activateUser(String email, String activateString);

	User updateUser(User user);

	User getUserByUsername(String username);

	Friendship createFriendship(Friendship fs);

	Friendship acceptFriendship(Friendship fs);

	boolean deleteFriend(Friendship fs);

	Collection<User> allFriends(User user);

	Friendship notAcceptFriendship(Friendship notAcceptFSRequest);

	Collection<User> allFriendshipRequest(User user);

	Collection<User> allNotFriends(User user);

}
