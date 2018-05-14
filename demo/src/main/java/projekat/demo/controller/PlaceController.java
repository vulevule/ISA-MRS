package projekat.demo.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import projekat.demo.exceptions.ArenaException;
import projekat.demo.exceptions.PlaceException;
import projekat.demo.model.Arena;
import projekat.demo.model.Place;
import projekat.demo.service.PlaceService;

@Controller
@RequestMapping("/places")
public class PlaceController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PlaceService placeService;

	@RequestMapping(value = "/getPlaces", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Place>> getPlaces() {
		logger.info("> getPlaces");

		Collection<Place> places = placeService.findAll();

		logger.info("< getPlaces");

		return new ResponseEntity<Collection<Place>>(places, HttpStatus.OK);
	}

	@RequestMapping(value = "/getArenas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Arena>> getArenas() {
		logger.info("> getArenas");

		Collection<Arena> arenas = placeService.findAllArenas();

		logger.info("< getArenas");

		return new ResponseEntity<Collection<Arena>>(arenas, HttpStatus.OK);
	}

	@PostMapping(value = "/createPlace", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlaceException> createPlace(@RequestBody Place p) {
		logger.info("> adding cinema/theater");

		Place createPlace = null;
		PlaceException ue = new PlaceException(createPlace, "");
		try {
			createPlace = placeService.createPlace(p);
			if (createPlace == null) {
				ue.setMessage("Already exist cinema/theater with the same name");
				ue.setPlace(createPlace);
				return new ResponseEntity<PlaceException>(ue, HttpStatus.ALREADY_REPORTED);
			}

			ue.setPlace(createPlace);
			ue.setMessage("Place has been successfully created");

		} catch (Exception e) {
			ue.setMessage(e.getMessage());
			return new ResponseEntity<PlaceException>(ue, HttpStatus.EXPECTATION_FAILED);
		}

		logger.info("> adding cinema/theater");
		return new ResponseEntity<PlaceException>(ue, HttpStatus.CREATED);

	}

	@PostMapping(value = "/createArena", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArenaException> createArena(@RequestBody Arena a) {
		logger.info("> adding arena");

		Arena createArena = null;
		ArenaException ae = new ArenaException(createArena, "");
		try {
			createArena = placeService.createArena(a);

			if (createArena == null) {
				ae.setMessage("Arena with same name and place already exists!");
				ae.setArena(createArena);
				return new ResponseEntity<ArenaException>(ae, HttpStatus.ALREADY_REPORTED);
			}

			ae.setArena(createArena);
			ae.setMessage("Arena has been successfully created");

		} catch (Exception e) {
			ae.setMessage(e.getMessage());
			return new ResponseEntity<ArenaException>(ae, HttpStatus.EXPECTATION_FAILED);
		}

		logger.info("> adding cinema/theater");
		return new ResponseEntity<ArenaException>(ae, HttpStatus.CREATED);
	}

}
