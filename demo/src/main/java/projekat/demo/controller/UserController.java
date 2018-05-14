package projekat.demo.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import projekat.demo.exceptions.FriendshipException;
import projekat.demo.exceptions.UserException;
import projekat.demo.model.ActivateAccount;
import projekat.demo.model.Friendship;
import projekat.demo.model.LoginUser;
import projekat.demo.model.RoleType;
import projekat.demo.model.User;
import projekat.demo.service.EmailService;
import projekat.demo.service.UserService;

@RestController
public class UserController {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	@Autowired
	private EmailService emailService;

	@Autowired
	private HttpSession session;

	@GetMapping(value = "users/exists", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserException> UserInSession() {
		logger.info("> exists user");
		User user = (User) session.getAttribute("loginUser");
		UserException ue = new UserException(user, "Exists login user");
		if (user == null) {
			ue.setMessage("Does not exist login user");
		}
		logger.info("<< exists user" + user);
		return new ResponseEntity<UserException>(ue, HttpStatus.OK);
	}

	@PostMapping(value = "users/registrationUser", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserException> registrationUser(@RequestBody User u) {
		logger.info(">> registration");

		User createUser = null;
		UserException ue = new UserException(createUser, "");

		// pprovera da li se lozinke poklapaju
		if (!(u.getPassword().equals(u.getRepeatPassword()))) {
			// baca gresku
			logger.info("<< registration");
			ue.setMessage("Entered passwords do not matched!!");
			return new ResponseEntity<>(ue, HttpStatus.BAD_REQUEST);
		}

		try {
			createUser = userService.createUser(u);
			if (createUser == null) {
				ue.setMessage("Already exist user with enterd username or email");
				ue.setUser(createUser);
				return new ResponseEntity<UserException>(ue, HttpStatus.ALREADY_REPORTED);
			}
			ue.setUser(createUser);
			ue.setMessage("User has been successfully created");
			if (createUser.getType() == RoleType.VISITOR) {
				if (sendActivateMail(createUser) == false) {
					// ne uspelo slanje mejla, ispisujemo poruku
					ue.setMessage("Failed to send activation email.");
					return new ResponseEntity<UserException>(ue, HttpStatus.BAD_REQUEST);
				}
			}
		} catch (Exception e) {
			ue.setMessage(e.getMessage());
			return new ResponseEntity<UserException>(ue, HttpStatus.EXPECTATION_FAILED);
		}

		logger.info("<< registration");
		return new ResponseEntity<UserException>(ue, HttpStatus.CREATED);

	}

	private boolean sendActivateMail(User createUser) {
		// TODO Auto-generated method stub

		// send mail to activate account
		if (createUser.getType() == RoleType.VISITOR) {
			try {
				emailService.sendNotification(createUser);
				// uneti u bazu aktivacioni string
				userService.setActivateString(createUser);
				return true;
			} catch (Exception e) {
				logger.info("Error when sending email: " + e.getMessage());
				return false;
			}
		}

		return false;
	}

	@PostMapping(value = "users/activateAccount", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserException> activateAccount(@RequestBody ActivateAccount aa) {
		logger.info(">> activate Account");
		User u = this.userService.activateUser(aa.getEmail(), aa.getActivateAccount());
		UserException ue = new UserException(u, "");
		if (u == null) {
			ue.setMessage("Unsuccessful activation");
			return new ResponseEntity<UserException>(ue, HttpStatus.NOT_FOUND);
		} else {
			ue.setMessage("Successful activation");
		}
		logger.info("<< activate Account");
		return new ResponseEntity<UserException>(ue, HttpStatus.OK);

	}

	@GetMapping("users/activate")
	public ModelAndView activateForm() {
		logger.info("> activate form");

		logger.info("< activate form");

		return new ModelAndView("activate");
	}

	@PostMapping(value = "users/loginUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserException> loginUser(@RequestBody LoginUser lu) {
		logger.info("> login");

		User u = userService.login(lu.getUsername(), lu.getPassword());
		UserException ue = new UserException(u, "Successful login");
		if (u == null) {
			ue.setMessage("Invalidate username or password");
			return new ResponseEntity<UserException>(ue, HttpStatus.NOT_FOUND);
		}
		logger.info("< login");
		session.setAttribute("loginUser", u);
		return new ResponseEntity<UserException>(ue, HttpStatus.OK);
	}

	@PostMapping(value = "users/updateAccount", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserException> updateAccount(@RequestBody User user) {
		logger.info(">> update account");
		User u = this.userService.updateUser(user);
		UserException ue = new UserException(u, "");

		if (u == null) {
			ue.setMessage("User does not exists");
			logger.info("<< update account");
			return new ResponseEntity<UserException>(ue, HttpStatus.ALREADY_REPORTED);
		} else {
			ue.setMessage("Successful update account");
		}
		logger.info("<< update account");
		return new ResponseEntity<UserException>(ue, HttpStatus.OK);

	}

	@PostMapping(value = "users/addFriend", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FriendshipException> addFriend(@RequestBody Friendship fs) {
		// slanje zahteva za prijateljstvo, samo unos podataka u tabelu friendship
		logger.info(">> add friend");
		User sender = (User) session.getAttribute("loginUser");
		fs.setSender(sender);
		Friendship newFriendship = this.userService.createFriendship(fs);
		FriendshipException fe = new FriendshipException(newFriendship, "");
		if (newFriendship == null) {
			fe.setMessage("Requset for friendship has not been sent");
			logger.info("<< add friend");
			return new ResponseEntity<FriendshipException>(fe, HttpStatus.BAD_REQUEST);
		}

		fe.setMessage("The request for friendship has been successfully sent");
		logger.info("<< add friend");
		return new ResponseEntity<FriendshipException>(fe, HttpStatus.CREATED);

	}

	@PostMapping(value = "users/acceptFriendship", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FriendshipException> acceptFriendship(@RequestBody Friendship fs) {
		logger.info(">> accept friendship");
		fs.setSender((User) session.getAttribute("loginUser"));
		Friendship acceptFriendship = this.userService.acceptFriendship(fs);
		FriendshipException fe = new FriendshipException(acceptFriendship, "");
		if (acceptFriendship == null) {
			fe.setMessage("Does not exist friendship");
			logger.info("<< accept friendship");
			return new ResponseEntity<FriendshipException>(fe, HttpStatus.BAD_REQUEST);
		}

		fe.setMessage("A request for friendship is accepted");
		logger.info("<< accept friendship");
		return new ResponseEntity<FriendshipException>(fe, HttpStatus.OK);

	}

	@PostMapping(value = "users/deleteFriendship", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FriendshipException> deleteFriend(@RequestBody Friendship fs) {
		logger.info(">> delete friend");
		fs.setSender((User) session.getAttribute("loginUser"));
		boolean deleteFriendship = this.userService.deleteFriend(fs);
		FriendshipException fe = new FriendshipException(null, "");
		if (deleteFriendship == false) {
			fe.setMessage("Does not exist friendship");
			logger.info("<< delete friend");
			return new ResponseEntity<FriendshipException>(fe, HttpStatus.BAD_REQUEST);
		}

		fe.setMessage("Friendship has been deleted");
		logger.info("<< delete friend");
		return new ResponseEntity<FriendshipException>(fe, HttpStatus.OK);

	}

}
