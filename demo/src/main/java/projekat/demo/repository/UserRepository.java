package projekat.demo.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import projekat.demo.model.RoleType;
import projekat.demo.model.User;

public interface UserRepository extends CrudRepository<User, String> {

	User findByEmailAndPasswordAndActivate(String username, String password, boolean activate);

	User findByEmail(String email);
	
	User findByEmailAndActivateString(String email, String activateString);

	Collection<User> findByType(RoleType visitor);

}
