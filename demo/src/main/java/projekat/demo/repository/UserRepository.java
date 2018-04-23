package projekat.demo.repository;


import projekat.demo.model.User;


public interface UserRepository {

	boolean createUser(User u);
	
	User login(String username, String password);
}
