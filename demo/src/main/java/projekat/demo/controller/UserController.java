package projekat.demo.controller;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import projekat.demo.dto.UserDTO;
import projekat.demo.exceptions.FriendshipException;
import projekat.demo.exceptions.UserException;
import projekat.demo.model.Friendship;
import projekat.demo.model.LoginUser;
import projekat.demo.model.RoleType;
import projekat.demo.model.User;
import projekat.demo.model.Visitor;
import projekat.demo.service.EmailService;
import projekat.demo.service.UserService;

@RestController
public class UserController {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	@Autowired
	private EmailService emailService;

	

	@GetMapping(value = "users/exists", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserException> userInSession(HttpSession session) {
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
	public ResponseEntity<UserException> registrationUser(@RequestBody UserDTO u) {
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
			if (u.getType() == RoleType.VISITOR) {
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
				Visitor v = (Visitor) createUser;

				try {
					emailService.sendNotification(v);
					// uneti u bazu aktivacioni string
					userService.setActivateString(v);
					return true;
				} catch (Exception e) {
					logger.info("Error when sending email: " + e.getMessage());
					return false;
				}
	}

	@RequestMapping(value="users/activate{email}", method = RequestMethod.GET)
	public RedirectView activateAccount( @RequestParam("email") String email) {
		logger.info(">> activate Account");
		Boolean activateAccount = this.userService.activateUser(email);
		if(activateAccount == false){
			//nije uspesno aktiviran nalog, otici na neku stranicu koja ce da ispise da nije aktiviran nalog
		}
		
		logger.info("<< activate Account");
		return new RedirectView("http://localhost:8080");//uspesno aktiviran nalog, korisnik moze da se uloguje

	}

	
	@PostMapping(value = "users/loginUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserException> loginUser(@RequestBody LoginUser lu, HttpSession session) {
		logger.info("> login");
		if ((User) session.getAttribute("loginUser") != null) {
			return new ResponseEntity<UserException>(new UserException(null, "Already exists login user"),
					HttpStatus.BAD_REQUEST);
		}
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

	@GetMapping(value = "users/logOut", produces = MediaType.APPLICATION_JSON_VALUE)
	public RedirectView logOut(HttpSession session) {
		logger.info("log out");
		User loginUser = (User) session.getAttribute("loginUser");
		if(loginUser != null){
			session.setAttribute("loginUser", null);
			return new RedirectView("http://localhost:8080");
			
		}
		return new RedirectView("http://localhost:8080");

	}
	
	@PostMapping(value = "users/updateAccount", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserException> updateAccount(@RequestBody UserDTO user, HttpSession session) {
		logger.info(">> update account");

		// user sa sesije se menja
		User sessionUser = (User) session.getAttribute("loginUser");
		logger.info("Update user: email: " + sessionUser.getEmail() + " name: " + sessionUser.getName() + " surname: "
				+ sessionUser.getSurname() + " address: " + sessionUser.getAddress() + " phone: "
				+ sessionUser.getPhone() + " password: " + sessionUser.getPassword());

		// ovi atributi se ne mogu menjati
		user.setEmail(sessionUser.getEmail());
		user.setType(sessionUser.getRole());

		logger.info("Update user: email: " + user.getEmail() + " name: " + user.getFirstName() + " surname: "
				+ user.getLastName() + " address: " + user.getAddress() + " phone: " + user.getPhone() + " password: "
				+ user.getPassword());

		UserException ue = new UserException(sessionUser, "");
		if (!(user.getPassword().equals(user.getRepeatPassword()))) {
			ue.setMessage("Entered passwords do not matched!!");
			return new ResponseEntity<UserException>(ue, HttpStatus.BAD_REQUEST);
		}

		User updateUser = null;

		if (user.getType() == RoleType.VISITOR) {
			Visitor updateV = new Visitor(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(),
					user.getAddress(), user.getPhone(), true, user.getType());
			updateUser = this.userService.updateUser(updateV);
		}
		ue.setUser(updateUser);

		if (updateUser == null) {
			ue.setMessage("User does not exists");
			logger.info("<< update account");
			return new ResponseEntity<UserException>(ue, HttpStatus.ALREADY_REPORTED);
		} else {
			ue.setMessage("Successful update account");
		}
		session.setAttribute("loginUser", user);
		logger.info("<< update account");
		return new ResponseEntity<UserException>(ue, HttpStatus.OK);

	}


	@PostMapping(value = "users/addFriend", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FriendshipException> addFriend(@RequestBody Visitor receiver, HttpSession session) {
		// sender - sa sesije, receiver - prosledjeni user, status - send
		logger.info(">> add friend");
		logger.info("add user: " + receiver.getEmail());
		// uzmemo kompletnog korisnika iz baze na osnovu email-a
		Visitor sender = (Visitor) session.getAttribute("loginUser"); // user sa
		// sesije
		Friendship newFriendship = this.userService.createFriendship(sender.getEmail(), receiver.getEmail());
		FriendshipException fe = new FriendshipException(newFriendship, "");
		if (newFriendship == null) {
			fe.setMessage("Requset for friendship has not been sent");
			logger.info("<< add friend");
			return new ResponseEntity<FriendshipException>(fe, HttpStatus.BAD_REQUEST);
		}
		
		//poslati mejl korisniku kojem je poslat zahtev za prijateljstvo
		

		fe.setMessage("The request for friendship has been successfully sent");
		logger.info("<< add friend");
		return new ResponseEntity<FriendshipException>(fe, HttpStatus.CREATED);

	}
	
	@PostMapping(value = "users/acceptFriendship", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FriendshipException> acceptFriendship(@RequestBody Visitor sender, HttpSession session) {
		// receiver-> user sa sesije (primio zahtev i sad ga prihvata), sender
		// <- user , status - approved
		logger.info(">> accept friendship");
		// receiver sa sesije
		Visitor receiver = (Visitor) session.getAttribute("loginUser");
		Friendship acceptFriendship = this.userService.acceptFriendship(sender.getEmail(), receiver.getEmail());
		FriendshipException fe = new FriendshipException(acceptFriendship, "");
		if (acceptFriendship == null) {
			fe.setMessage("Does not exist send request");
			logger.info("<< accept friendship");
			return new ResponseEntity<FriendshipException>(fe, HttpStatus.BAD_REQUEST);
		}

		fe.setMessage("A request for friendship is accepted");
		logger.info("<< accept friendship");
		return new ResponseEntity<FriendshipException>(fe, HttpStatus.OK);

	}
	
	@PostMapping(value = "users/notAcceptFriendship", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FriendshipException> notAcceptFriendship(@RequestBody Visitor user, HttpSession session) {
		logger.info(">> not accept friendship request");
		// sender <- user, receiver <- user sa sesije, status - not approved
		Visitor receiver = (Visitor) session.getAttribute("loginUser");
		logger.info("sender: " + user.getEmail() + ", receiver: " + receiver.getEmail());
		Friendship notAcceptFriendship = this.userService.notAcceptFriendship(user.getEmail(), receiver.getEmail());
		FriendshipException fe = new FriendshipException(notAcceptFriendship, "");
		if (notAcceptFriendship == null) {
			fe.setMessage("Does not exist friendship");
			logger.info("<< NOT ACCEPT FRIENDSHIP");
			return new ResponseEntity<FriendshipException>(fe, HttpStatus.BAD_REQUEST);
		}
		fe.setMessage("A request for friendship is not accept");
		logger.info("<< not accept friendship request");
		return new ResponseEntity<FriendshipException>(fe, HttpStatus.OK);

	}
	
	@PostMapping(value = "users/deleteFriendship", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FriendshipException> deleteFriend(@RequestBody Visitor user, HttpSession session) {
		logger.info(">> delete friend");
		logger.info(">> delete friend: " + user.getEmail());
		// prosledicemo servisu i sendera i receivera pa cemo tamo odraditi
		// ostalu logiku
		Visitor sessionUser = (Visitor) session.getAttribute("loginUser");
		boolean deleteFriendship = this.userService.deleteFriend(user.getEmail(), sessionUser.getEmail());
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
	//all friends
	@RequestMapping(value= "users/allFriends", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<User>> getAllFriends(HttpSession session){
			logger.info("> all friends");
			User user =(User)session.getAttribute("loginUser");
			Collection<User> allFriends = this.userService.allFriends(user);			
			logger.info("< all friends");
			return new ResponseEntity<Collection<User>>(allFriends, HttpStatus.OK);
			
		}
	//all friendship request
	@RequestMapping(value= "users/allFriendshipRequest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<User>> getAllFriendshipRequest(HttpSession session){
			logger.info("> all friendship request");
			User user =(User)session.getAttribute("loginUser");
			Collection<User> allFriends = this.userService.allFriendshipRequest(user);			
			logger.info("< all friendship request");
			return new ResponseEntity<Collection<User>>(allFriends, HttpStatus.OK);
			
		}
	
	// all not friends
	@RequestMapping(value = "users/allNotFriends", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<User>> getAllNotFriends(HttpSession session) {
			logger.info(">> all not friends");
			Visitor user = (Visitor)session.getAttribute("loginUser");
			Collection<User> allNotFriends = this.userService.allNotFriends(user);
			logger.info("<< all not friends");
			return new ResponseEntity<Collection<User>>(allNotFriends, HttpStatus.OK);

		}

}
