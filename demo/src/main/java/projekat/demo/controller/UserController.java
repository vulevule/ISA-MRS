package projekat.demo.controller;

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

import projekat.demo.model.ActivateAccount;
import projekat.demo.model.LoginUser;
import projekat.demo.model.RoleType;
import projekat.demo.model.User;
import projekat.demo.model.UserException;
import projekat.demo.service.EmailService;
import projekat.demo.service.UserService;

@RestController
public class UserController {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	@Autowired
	private EmailService emailService;

	@GetMapping("users/register")
	public ModelAndView registration() {
		logger.info("> registration form");

		logger.info("< registration form");

		return new ModelAndView("registration");
	}

	@PostMapping(value = "users/registrationUser", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserException> registrationUser(@RequestBody User u) {
		logger.info("> registration");

		User createUser = null;
		UserException ue = new UserException(createUser, "");
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
				sendActivateMail(createUser);
			}
		} catch (Exception e) {
			ue.setMessage(e.getMessage());
			return new ResponseEntity<UserException>(ue, HttpStatus.EXPECTATION_FAILED);
		}

		logger.info("> registration");
		return new ResponseEntity<UserException>(ue, HttpStatus.CREATED);

	}

	private void sendActivateMail(User createUser) {
		// TODO Auto-generated method stub

		// send mail to activate account
		if (createUser.getType() == RoleType.VISITOR) {
			try {
				emailService.sendNotification(createUser);
				// uneti u bazu aktivacioni string
				userService.setActivateString(createUser);
			} catch (Exception e) {
				logger.info("Error when sending email: " + e.getMessage());
			}
		}
	}

	@PostMapping(value = "users/activateAccount", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserException> activateAccount(ActivateAccount aa) {
		User u = this.userService.activateUser(aa.getEmail(), aa.getActivateAccount());
		UserException ue = new UserException(u, "");
		if (u == null) {
			ue.setMessage("Unsuccessful activation");
		} else {
			ue.setMessage("Successful activation");
		}

		return new ResponseEntity<UserException>(ue, HttpStatus.OK);

	}

	@GetMapping("users/login")
	public ModelAndView loginForm() {
		logger.info("> login form");

		logger.info("< login form");

		return new ModelAndView("login");
	}

	@PostMapping(value = "users/loginUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserException> loginUser(@RequestBody LoginUser lu) {
		logger.info("> login");

		User u = userService.login(lu.getUsername(), lu.getPassword());
		UserException ue = new UserException(u, "Successful login");
		if (u == null) {
			ue.setMessage("Invalidate username or password");
			return new ResponseEntity<UserException>(ue, HttpStatus.BAD_REQUEST);
		}
		logger.info("< login");
		return new ResponseEntity<UserException>(ue, HttpStatus.OK);
	}
	
	@PostMapping(value = "users/updateAccount", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserException> updateAccount(@RequestBody User user) {
		User u = this.userService.updateUser(user);
		UserException ue = new UserException(u, "");
		
		if (u == null) {
			ue.setMessage("User does not exists");
			return new ResponseEntity<UserException>(ue, HttpStatus.ALREADY_REPORTED);
		} else {
			ue.setMessage("Successful update account");
		}

		return new ResponseEntity<UserException>(ue, HttpStatus.OK);

	}
	

}
