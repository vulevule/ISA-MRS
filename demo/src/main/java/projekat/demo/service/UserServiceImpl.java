package projekat.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekat.demo.model.RoleType;
import projekat.demo.model.User;
import projekat.demo.model.Visitor;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserServiceImpl userService;
	
	@Override
	public boolean createUser(User u) {
		// TODO Auto-generated method stub
		boolean create = false;
		if (u.getType() == RoleType.VISITOR){
			Visitor v = (Visitor)u;
			v.setActivate(false);
			create = userService.createUser(v);
		}
		//create = userService.createUser(u);
		if(create){
			return true;
		}
		return false;
	}

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		User loginUser = userService.login(username, password);
		if (loginUser != null){
			return loginUser;
		}
		return null;
	}

}
