package projekat.demo.service;

import java.util.Collection;

import projekat.demo.model.Friendship;
import projekat.demo.model.User;
import projekat.demo.model.DTO.UserDTO;

public interface UserService {

	User createUser(UserDTO u);

	User login(String username, String password);

	User setActivateString(User createUser);

	boolean activateUser( String email);

	User updateUser(User user);

	User getUserByUsername(String username);

	Friendship createFriendship(String sender, String receiver);

	Friendship acceptFriendship(String sender, String receiver);

	boolean deleteFriend(String user, String sessionUser);

	Collection<User> allFriends(User user);

	Friendship notAcceptFriendship(String sender, String receiver);

	Collection<User> allFriendshipRequest(User user);

	Collection<User> allNotFriends(User user);

}
