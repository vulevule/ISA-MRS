package projekat.demo.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import projekat.demo.model.Place;
import projekat.demo.model.PlaceException;
import projekat.demo.model.RoleType;
import projekat.demo.model.User;
import projekat.demo.model.UserException;
import projekat.demo.service.PlaceService;


@Controller
@RequestMapping("/places")
public class PlaceController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PlaceService placeService;
	
	
	@GetMapping
	public ModelAndView getPlaces(){
		logger.info("> getPlaces");
		
		//ArrayList<Place> places = placeService.findAll();
		
		logger.info("< getPlaces");
		
		return new ModelAndView("index");
	}
	
	@PostMapping(
			value = "/createPlaces",
			consumes = "application/json",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlaceException> createPlace(@RequestBody Place p){
		logger.info("> adding cinema/theater");
		
		Place createPlace = null;
		PlaceException ue = new PlaceException(createPlace, "");
		try{
			createPlace = placeService.createPlace(p);
			if (createPlace == null){
				ue.setMessage("Already exist cinema/theater with the same name");
				ue.setPlace(createPlace);
				return new ResponseEntity<PlaceException>(ue, HttpStatus.ALREADY_REPORTED);
			}
			ue.setPlace(createPlace);
			ue.setMessage("Place has been successfully created");
			
		}catch (Exception e){
			ue.setMessage(e.getMessage());
			return new ResponseEntity<PlaceException>(ue, HttpStatus.EXPECTATION_FAILED);
		}
		
		logger.info("> adding cinema/theater");
		return new ResponseEntity<PlaceException>(ue, HttpStatus.CREATED);
		
	}
}
