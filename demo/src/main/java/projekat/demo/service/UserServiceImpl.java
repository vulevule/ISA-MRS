package projekat.demo.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import projekat.demo.model.FanZoneAdmin;
import projekat.demo.model.Friendship;
import projekat.demo.model.FriendshipStatus;
import projekat.demo.model.PlaceAdmin;
import projekat.demo.model.RoleType;
import projekat.demo.model.User;
import projekat.demo.model.Visitor;
import projekat.demo.model.DTO.UserDTO;
import projekat.demo.repository.FriendshipRepository;
import projekat.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FriendshipRepository friendshipRepository;

	@Override
	public User createUser(UserDTO u) {
		// treba navesti da nijedan atribut ne sme biti null
		User findUser = this.userRepository.findByEmail(u.getEmail());
		if (findUser != null) {
			return null;
		}

		if (u.getType() == RoleType.VISITOR) {
			Visitor v = new Visitor(u.getFirstName(), u.getLastName(), u.getEmail(), u.getPassword(), u.getAddress(),
					u.getPhone(), false, u.getType());
			return this.userRepository.save(v);
		} else if (u.getType() == RoleType.CINEMA_THEATER_ADMIN) {
			return null;
		} else if (u.getType() == RoleType.FAN_ZONE_ADMIN) {
			return null;
		} else if (u.getType() == RoleType.SYSTEM_ADMIN) {
			// dodati kod za registrovanje admina
			return null;
		}
		return null;
	}

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		Assert.notNull(password, "The password must not be null");
		Assert.notNull(username, "The username must not be null");
		boolean activate = true;
		User u = this.userRepository.findByEmailAndPassword(username, password);
		if (u.getRole() == RoleType.VISITOR) {
			Visitor v = (Visitor) u;
			if (v.isActivate() == true) {
				return v;
			} else {
				return null; // ne moze da se uloguje, nije aktiviran nalog
			}
		}

		return u;
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
		Visitor v = (Visitor) u;
		if (v.getActivateString().equals(activateString)) {
			v.setActivate(true);
			return this.userRepository.save(v);
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
		// upis u bazu zahteva za prijateljstvo
		Friendship saveFriendShip = new Friendship();
		saveFriendShip.setSender(this.userRepository.findByEmail(fs.getSender().getEmail()));
		saveFriendShip.setReceiver(this.userRepository.findByEmail(fs.getReceiver().getEmail()));
		saveFriendShip.setStatus(fs.getStatus());
		return this.friendshipRepository.save(saveFriendShip);
	}

	@Override
	public Friendship acceptFriendship(Friendship fs) {
		// TODO Auto-generated method stub
		Friendship acceptFriendship = new Friendship();
		acceptFriendship.setSender(this.userRepository.findByEmail(fs.getSender().getEmail()));
		acceptFriendship.setReceiver(this.userRepository.findByEmail(fs.getReceiver().getEmail()));
		acceptFriendship.setStatus(fs.getStatus());

		Friendship searchFriendship = this.friendshipRepository.findBySenderAndReceiverAndStatus(
				acceptFriendship.getSender(), acceptFriendship.getReceiver(), FriendshipStatus.SEND_REQUEST);
		if (searchFriendship == null) {
			// ne postoji, nije moguce promeniti mu status
			return null;
		}

		// sacuvamo novo stanje sa promenom statusa
		searchFriendship.setStatus(fs.getStatus());
		return this.friendshipRepository.save(searchFriendship);
	}

	@Override
	public boolean deleteFriend(User user, User sessionUser) {
		// 1. sender <- sessionUser, receiver <- user
		User u = this.userRepository.findByEmail(user.getEmail());
		Friendship deleteFriendship1 = new Friendship(FriendshipStatus.APPROVED, sessionUser, u);
		// 2. sender <- user, receiver <- sessionUser
		Friendship deleteFriendship2 = new Friendship(FriendshipStatus.APPROVED, u, sessionUser);

		Friendship searchFriendship1 = this.friendshipRepository.findBySenderAndReceiverAndStatus(
				deleteFriendship1.getSender(), deleteFriendship1.getReceiver(), deleteFriendship1.getStatus());

		if (searchFriendship1 == null) {
			Friendship searchFriendship2 = this.friendshipRepository.findBySenderAndReceiverAndStatus(
					deleteFriendship2.getSender(), deleteFriendship2.getReceiver(), deleteFriendship2.getStatus());
			if (searchFriendship2 == null) {
				return false;
			}
			this.friendshipRepository.delete(searchFriendship2);
			return true;
		}

		friendshipRepository.delete(searchFriendship1);
		return true;
	}

	@Override
	public Friendship notAcceptFriendship(Friendship notAcceptFSRequest) {
		//
		Friendship searchFriendship = this.friendshipRepository.findBySenderAndReceiverAndStatus(
				notAcceptFSRequest.getSender(), notAcceptFSRequest.getReceiver(), FriendshipStatus.SEND_REQUEST);
		if (searchFriendship == null) {
			// ne postoji prijateljstvo
			return null;
		}
		searchFriendship.setStatus(notAcceptFSRequest.getStatus());
		return this.friendshipRepository.save(searchFriendship);
	}

	@Override
	public Collection<User> allFriends(User user) {
		Collection<Friendship> sendFriend = this.friendshipRepository.findBySenderAndStatus(user,
				FriendshipStatus.APPROVED);
		Collection<Friendship> acceptFriend = this.friendshipRepository.findByReceiverAndStatus(user,
				FriendshipStatus.APPROVED);
		Collection<User> friends = new ArrayList<User>();

		for (Friendship fs : sendFriend) {
			friends.add(fs.getReceiver());
		}
		for (Friendship fs : acceptFriend) {
			friends.add(fs.getSender());
		}
		return friends;
	}

	@Override
	public Collection<User> allFriendshipRequest(User user) {
		// pronadjemo sve poslate zahteve, trazimo da prosledjeni user bude
		// receiver i status da bude send
		Collection<Friendship> requests = this.friendshipRepository.findByReceiverAndStatus(user,
				FriendshipStatus.SEND_REQUEST);
		Collection<User> users = new ArrayList<User>();
		for (Friendship fs : requests) {
			users.add(fs.getSender());
		}
		return users;
	}

	// all send request
	public Collection<User> allSendRequests(User sender) {
		Collection<Friendship> sendRequests = this.friendshipRepository.findBySenderAndStatus(sender,
				FriendshipStatus.SEND_REQUEST);
		Collection<User> users = new ArrayList<User>();
		for (Friendship fs : sendRequests) {
			users.add(fs.getReceiver());
		}
		return users;
	}

	@Override
	public Collection<User> allNotFriends(User user) {
		// 1. uzmemo sve posetioce; 2. uzmemo sve prijatelje i dobijene zahteve
		// //treba izbaciti usera
	/*	Collection<User> allVisitors = this.userRepository.findByType(RoleType.VISITOR);
		// sad izbacimo usera koji je na sesiji
		for (User u : allVisitors) {
			if (u.getEmail().equals(user.getEmail())) {
				allVisitors.remove(u);
			}
		}
		// svi neprihvaceni zahtevi
		Collection<Friendship> allNotAcceptFriend = this.friendshipRepository.findBySenderAndStatus(user,
				FriendshipStatus.NOT_APPROVED);
		Collection<User> notAcceptUsers = new ArrayList<User>();
		for (Friendship fs : allNotAcceptFriend) {
			notAcceptUsers.add(fs.getReceiver());
		}
		Collection<Friendship> allNotAcceptFriend2 = this.friendshipRepository.findByReceiverAndStatus(user,
				FriendshipStatus.NOT_APPROVED);
		Collection<User> notAcceptUsers2 = new ArrayList<User>();
		for (Friendship fs : allNotAcceptFriend2) {
			notAcceptUsers2.add(fs.getSender());
		}

		Collection<User> friends = allFriends(user);
		friends.addAll(allFriendshipRequest(user));
		friends.addAll(allSendRequests(user));
		Collection<User> allNotFriends = new ArrayList<User>();
		for (User u : allVisitors) {
			if (!(friends.contains(u))) {
				allNotFriends.add(u);
			}
		}
		allNotFriends.addAll(notAcceptUsers);
		allNotFriends.addAll(notAcceptUsers2);
		return allNotFriends;*/
		return null;
	}

}
