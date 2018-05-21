package projekat.demo.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projekat.demo.dto.ThematicPropDto;
import projekat.demo.exceptions.ProjectionException;
import projekat.demo.exceptions.ThematicPropException;
import projekat.demo.exceptions.UserException;
import projekat.demo.service.ThematicPropService;

@RestController
@RequestMapping("/thematicprops")
public class ThematicPropController {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ThematicPropService thematicPropService;

	@PostMapping(value = "/createThematicProp", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createThematicProp(@RequestBody ThematicPropDto tp) {
		logger.info("> adding thematic prop started");

		try {
			thematicPropService.createThematicProp(tp);
			logger.info("< adding thematic prop");
			return new ResponseEntity<String>("Thematic Prop successfully created!", HttpStatus.CREATED);
		} catch (ProjectionException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		} catch (UserException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		} catch (ThematicPropException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.ALREADY_REPORTED);
		}
	}

	@PostMapping(value = "/updateThematicProp", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateThematicProp(@RequestBody ThematicPropDto tp) {
		logger.info("> updating thematic prop started");

		try {
			thematicPropService.updateThematicProp(tp);
			logger.info("< updating thematic prop");
			return new ResponseEntity<String>("Thematic Prop successfully created!", HttpStatus.OK);
		} catch (ProjectionException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		} catch (UserException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		} catch (ThematicPropException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteThematicProp(@RequestBody ThematicPropDto tp) {
		logger.info("> deleting thematic prop started");

		try {
			thematicPropService.deleteThematicProp(tp);
			logger.info("< deleting thematic prop");
			return new ResponseEntity<String>("Thematic Prop successfully deleted!", HttpStatus.OK);
		} catch (ProjectionException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		} catch (UserException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		} catch (ThematicPropException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

}
