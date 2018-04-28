package projekat.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekat.demo.model.RoleType;
import projekat.demo.model.User;
import projekat.demo.model.Visitor;
import projekat.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public boolean createUser(User u) {
		// TODO Auto-generated method stub
		User create = null;
		if (u.getType() == RoleType.VISITOR){
			Visitor v = (Visitor)u;
			v.setActivate(false);
			create = userRepository.save(v);
		}
		else {
			create = userRepository.save(u);
		}
		if(create != null){
			return true;
		}
		return false;
	}

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
	//	User loginUser = userRepository.findLoginUser(username, password);
	/*	if (loginUser != null){
			return loginUser;
		}*/
		return null;
	}

}
