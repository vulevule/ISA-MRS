package projekat.demo.service;

import projekat.demo.model.User;

public interface UserService {
	
	boolean createUser(User u);
	
	User login(String username, String password);
	
}
