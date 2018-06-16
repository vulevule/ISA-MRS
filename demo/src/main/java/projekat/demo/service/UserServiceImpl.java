package projekat.demo.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import projekat.demo.dto.UserDTO;
import projekat.demo.model.Admin;
import projekat.demo.model.FanZoneAdmin;
import projekat.demo.model.Friendship;
import projekat.demo.model.FriendshipStatus;
import projekat.demo.model.PlaceAdmin;
import projekat.demo.model.RoleType;
import projekat.demo.model.User;
import projekat.demo.model.Visitor;
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
		User u = this.userRepository.findByEmailAndPassword(username, password);
		if (u.getRole() == RoleType.VISITOR) {
			Visitor v = (Visitor) u;
			if (v.isActivate() == true) {
				return v;
			} else {
				return null; // ne moze da se uloguje, nije aktiviran nalog
			}
		}else if (u.getRole() == RoleType.SYSTEM_ADMIN){
			Admin a = (Admin) u;
			return a;
		}else if(u.getRole() == RoleType.FAN_ZONE_ADMIN){
			FanZoneAdmin fa = (FanZoneAdmin) u;
			return fa;
		}else if (u.getRole() == RoleType.CINEMA_THEATER_ADMIN){
			PlaceAdmin pa = (PlaceAdmin) u;
			return pa;
		}

		return u;
	}

	@Override
	public User setActivateString(User createUser) {
		// change user activate String
		return this.userRepository.save(createUser);

	}

	@Override
	public boolean activateUser(String email) {
		// activate account
		User u = this.userRepository.findByEmail(email);
		Visitor v = (Visitor) u;
		/*
		 * if (v.getActivateString().equals(activateString)) {
		 * v.setActivate(true); User vi = this.userRepository.save(v); return
		 * true; }
		 */
		v.setActivate(true);
		User vi = this.userRepository.save(v);

		return true;
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
	public Friendship createFriendship(String sender, String receiver) {
		// upis u bazu zahteva za prijateljstvo
		Friendship saveFriendShip = new Friendship(FriendshipStatus.SEND_REQUEST, sender, receiver);
		// mozemo jos samo da proverimo da li postoje posetioci sa ovim
		// emailovima
		User senderUser = this.userRepository.findByEmailAndRole(sender, RoleType.VISITOR);
		User receiverUser = this.userRepository.findByEmailAndRole(receiver, RoleType.VISITOR);

		if (senderUser == null || receiverUser == null) {
			return null;
		}

		return this.friendshipRepository.save(saveFriendShip);
	}

	@Override
	public Friendship acceptFriendship(String sender, String receiver) {
		// TODO Auto-generated method stub
		Friendship searchFriendship = this.friendshipRepository.findBySenderAndReceiverAndStatus(sender, receiver,
				FriendshipStatus.SEND_REQUEST);
		if (searchFriendship == null) {
			// ne postoji, nije moguce promeniti mu status
			return null;
		}

		// sacuvamo novo stanje sa promenom statusa
		searchFriendship.setStatus(FriendshipStatus.APPROVED);
		return this.friendshipRepository.save(searchFriendship);
	}

	@Override
	public boolean deleteFriend(String user, String sessionUser) {
		// 1. sender <- sessionUser, receiver <- user
		Friendship deleteFriendship1 = new Friendship(FriendshipStatus.APPROVED, sessionUser, user);
		// 2. sender <- user, receiver <- sessionUser
		Friendship deleteFriendship2 = new Friendship(FriendshipStatus.APPROVED, user, sessionUser);

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
	public Friendship notAcceptFriendship(String sender, String receiver) {
		//
		Friendship searchFriendship = this.friendshipRepository.findBySenderAndReceiverAndStatus(sender, receiver,
				FriendshipStatus.SEND_REQUEST);
		if (searchFriendship == null) {
			// ne postoji prijateljstvo
			return null;
		}
		searchFriendship.setStatus(FriendshipStatus.NOT_APPROVED);
		return this.friendshipRepository.save(searchFriendship);
	}

	@Override
	public Collection<User> allFriends(User user) {
		// TODO Auto-generated method stub
		Collection<Friendship> sendFriend = this.friendshipRepository.findBySenderAndStatus(user.getEmail(),
				FriendshipStatus.APPROVED);
		Collection<Friendship> acceptFriend = this.friendshipRepository.findByReceiverAndStatus(user.getEmail(),
				FriendshipStatus.APPROVED);
		Collection<User> friends = new ArrayList<User>();

		for (Friendship fs : sendFriend) {
			User u = this.userRepository.findByEmail(fs.getReceiver());
			friends.add(u);
		}
		for (Friendship fs : acceptFriend) {
			User u = this.userRepository.findByEmail(fs.getSender());
			friends.add(u);
		}
		return friends;
	}

	@Override
	public Collection<User> allFriendshipRequest(User user) {
		// pronadjemo sve poslate zahteve, trazimo da prosledjeni user bude
		// receiver i status da bude send
		Collection<Friendship> requests = this.friendshipRepository.findByReceiverAndStatus(user.getEmail(),
				FriendshipStatus.SEND_REQUEST);
		Collection<User> users = new ArrayList<User>();
		for (Friendship fs : requests) {
			User u = this.userRepository.findByEmail(fs.getSender());
			users.add(u);
		}
		return users;
	}


	// all send request
	public Collection<User> allSendRequests(User sender) {
		Collection<Friendship> sendRequests = this.friendshipRepository.findBySenderAndStatus(sender.getEmail(),
				FriendshipStatus.SEND_REQUEST);
		Collection<User> users = new ArrayList<User>();
		for (Friendship fs : sendRequests) {
			User u = this.userRepository.findByEmail(fs.getReceiver());
			users.add(u);
		}
		return users;
	}

	@Override
	public Collection<User> allNotFriends(User user) {
		// 1. uzmemo sve posetioce; 2. uzmemo sve prijatelje i dobijene zahteve
		// //treba izbaciti usera

		// 1. svi posetioci
		Collection<User> allVisitors = this.userRepository.findByRole(RoleType.VISITOR);
		// sad izbacimo usera koji je na sesiji
		for (User u : allVisitors) {
			if (u.getEmail().equals(user.getEmail())) {
				allVisitors.remove(u);
			}
		}

		// 2.svi prijatelji
		Collection<User> friends = allFriends(user);
		friends.addAll(allFriendshipRequest(user));
		friends.addAll(allSendRequests(user));

		Collection<User> allNotFriends = new ArrayList<User>();
		for (User u : allVisitors) {
			if (!(friends.contains(u))) {
				allNotFriends.add(u);
			}
		}
		return allNotFriends;

	}

	
}
