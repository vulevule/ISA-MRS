package projekat.demo.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import projekat.demo.model.User;


public interface UserRepository extends CrudRepository<User, String>{

	
	
	User findByEmailAndPassword(String username, String password);
	
	
}
