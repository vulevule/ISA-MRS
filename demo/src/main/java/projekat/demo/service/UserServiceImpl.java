package projekat.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import projekat.demo.model.User;
import projekat.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	
	@Override
	@org.springframework.transaction.annotation.Transactional(readOnly = false)
	public User createUser(User u) {
		//treba navesti da nijedan atribut ne sme biti null
		
		/*if (u.getType() == RoleType.VISITOR){
			Visitor v = (Visitor)u;
			v.setActivate(false);
			return userRepository.save(v);
		}
		else {
			return  userRepository.save(u);
		}*/
		
		return this.userRepository.save(u);
		
	}


		@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		Assert.notNull(password, "The password must not be null");
		Assert.notNull(username, "The username must not be null");
		return this.userRepository.findByEmailAndPassword(username, password);
		
	}

}
