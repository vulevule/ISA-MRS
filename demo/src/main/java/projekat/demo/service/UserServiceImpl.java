package projekat.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import projekat.demo.model.Friendship;
import projekat.demo.model.RoleType;
import projekat.demo.model.User;
import projekat.demo.repository.FriendshipRepository;
import projekat.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private FriendshipRepository friendshipRepository;
	
	@Override
	public User createUser(User u) {
		// treba navesti da nijedan atribut ne sme biti null

		if (u.getType() == RoleType.VISITOR) {
			u.setActivate(false); // posle cemo promeniti kada proradi slanje mejla 
		} else {
			u.setActivate(true); // svi ostali korisnici uvek imaju aktiviran nalog
		}

		User findUser = this.userRepository.findByEmail(u.getEmail());
		if (findUser == null) {
			return this.userRepository.save(u);
		}
		return null;

	}

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		Assert.notNull(password, "The password must not be null");
		Assert.notNull(username, "The username must not be null");
		boolean activate = true;
		return this.userRepository.findByEmailAndPasswordAndActivate(username, password, activate);

	}

	@Override
	public User setActivateString(User createUser) {
		// change user activate String
		return this.userRepository.save(createUser);

	}

	@Override
	public User activateUser(String email, String activateString) {
		// activate account
		User u = this.userRepository.findByEmailAndActivateString(email, activateString);

		if (u != null) {
			u.setActivate(true);
			return this.userRepository.save(u);
			
		}

		return null;
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method s
		return this.userRepository.save(user);
	}

	@Override
	public User getUserByUsername(String username) {
		return this.userRepository.findByEmail(username);
	}

	@Override
	public Friendship createFriendship(Friendship fs) {
		//upis u bazu zahteva za prijateljstvo 
		return this.friendshipRepository.save(fs);
	}

}
