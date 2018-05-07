package projekat.demo.service;

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

}
