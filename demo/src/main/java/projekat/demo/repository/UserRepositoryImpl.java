package projekat.demo.repository;

import org.springframework.stereotype.Repository;

import projekat.demo.model.RoleType;
import projekat.demo.model.User;
import projekat.demo.model.Visitor;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Override
	public boolean createUser(User u) {
		// TODO Auto-generated method stub
				
		//f-ja unosi korisnike u bazu
		return false;
	}

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		//upit u bazu 
		
		return null;
	}

	
	
}
