package projekat.demo.repository;


import org.springframework.data.repository.Repository;

import projekat.demo.model.User;


public interface UserRepository extends Repository<User, String>{

	User save(User u);
	
	User findAllByEmailAndPassword(String username, String password);
}
