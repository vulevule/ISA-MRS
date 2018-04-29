package projekat.demo.controller;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import projekat.demo.model.LoginUser;
import projekat.demo.model.User;
import projekat.demo.service.UserService;

@RestController
public class UserController {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@GetMapping("users/register")
	public ModelAndView registration() {
		logger.info("> registration form");

		logger.info("< registration form");

		return new ModelAndView("registration");
	}

	@PostMapping(
			value = "users/registrationUser",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> registrationUser(@RequestBody User u){
		logger.info("> registration");
		User createUser = null;
		try{
			createUser = userService.createUser(u);
		}catch (Exception e){
			
		}
		
		if (createUser == null)
		{
			logger.info("< registration ");
			return new ResponseEntity<User>(u, HttpStatus.BAD_REQUEST);
		}
		
		logger.info("> registration");
		return new ResponseEntity<User>(createUser, HttpStatus.CREATED);
		
	}
	
	@GetMapping("users/login")
	public ModelAndView loginForm() {
		logger.info("> login form");

		logger.info("< login form");

		return new ModelAndView("login");
	}
	
	@PostMapping(
			value = "users/loginUser",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> loginUser(@RequestBody LoginUser lu){
		logger.info("> login");
		
		User u = userService.login(lu.getUsername(), lu.getPassword());
		
		if(u == null)
		{
			logger.info("< login");
			return new ResponseEntity<User>(u, HttpStatus.NOT_FOUND);
		} 
		logger.info("< login");
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}
	
}
