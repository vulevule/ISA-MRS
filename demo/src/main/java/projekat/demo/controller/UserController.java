package projekat.demo.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import projekat.demo.model.LoginUser;
import projekat.demo.model.RoleType;
import projekat.demo.model.User;
import projekat.demo.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public ModelAndView registration() {
		logger.info("> registration form");

		logger.info("< registration form");

		return new ModelAndView("registration");
	}

	@PostMapping(value="/registrationUser")
	public ModelAndView registrationUser(@Valid @ModelAttribute("user") User user, BindingResult result){
		logger.info("> registration user");
		if(result.hasErrors()){
			logger.info("> Error <");
		}
		user.setType(RoleType.VISITOR);
		boolean create = userService.createUser(user);
		
		if (create==false){
			return new ModelAndView("registration");
		}
		
		logger.info("< registration user");
		return new ModelAndView("redirect/places");
		
	}
	
	@GetMapping("/login")
	public ModelAndView loginForm() {
		logger.info("> login form");

		logger.info("< login form");

		return new ModelAndView("login");
	}

	@PostMapping(value="/loginUser")
	
	public ModelAndView loginUser(@ModelAttribute("loginUser") LoginUser lu){
		logger.info("> login");
		if (lu.getUsername().equals("111") && lu.getPassword().equals("111")){
			logger.info("< login");
			return new ModelAndView("places");
		}
		
		
		return new ModelAndView("login");
	}
	
}
