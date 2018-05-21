package projekat.demo.repository;

import org.springframework.data.repository.CrudRepository;

import projekat.demo.model.User;

public interface UserRepository extends CrudRepository<User, String> {


	User findByEmail(String email);
	
	User findByEmailAndPassword(String username, String password);
	

}
