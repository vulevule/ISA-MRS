package projekat.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import projekat.demo.model.RoleType;
import projekat.demo.model.User;
import projekat.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(User u) {
		// treba navesti da nijedan atribut ne sme biti null

		if (u.getType() == RoleType.VISITOR) {
			u.setActivate(false); // tek kada aktivira nalog, ovo se menja na true
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
		User u = this.userRepository.findByEmail(email);

		if (u != null) {
			if (u.getActivateString().equals(activateString)) {
				// actvate account
				u.setActivate(true);
				return this.userRepository.save(u);
			}
		}

		return null;
	}

}
