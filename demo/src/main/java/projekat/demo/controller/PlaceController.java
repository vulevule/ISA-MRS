package projekat.demo.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import projekat.demo.model.Place;
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
		
		ArrayList<Place> places = placeService.findAll();
		
		logger.info("< getPlaces");
		
		return new ModelAndView("index", "places", places);
	}
	
}
