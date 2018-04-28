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

	@RequestMapping(
			value = "/registrationUser",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView registrationUser(@Valid @RequestBody User u){
		logger.info("> registration user");
		
		
		boolean create = userService.createUser(u);
		
		if (create==false){
			return new ModelAndView("registration");
		}
		
		logger.info("< registration user");
		return new ModelAndView("redirect/places");
		
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
		
		logger.info("> " + lu.getPassword());
		User u = userService.login(lu.getUsername(), lu.getPassword());
		if(lu.getUsername().equals("111") && lu.getPassword().equals("111")){
			return new ResponseEntity<User>(u, HttpStatus.OK);

		}
		if(u == null)
		{
			return null;
		} 
		logger.info("< login");
		return new ResponseEntity<User>(u, HttpStatus.BAD_REQUEST);
	}
	
}
