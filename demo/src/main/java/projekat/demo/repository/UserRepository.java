package projekat.demo.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import projekat.demo.model.User;


public interface UserRepository extends Repository<User, String>{

	User save(User u);
	
	User findAllByEmailAndPassword(String username, String password);
	/*
	@Query("select * from user where email=?1 and password=?2 and activate!=null")
	User findLoginUser(String username, String password);
	*/
	
}
