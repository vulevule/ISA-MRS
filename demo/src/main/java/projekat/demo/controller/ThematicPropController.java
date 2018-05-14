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

import projekat.demo.exceptions.ProjectionException;
import projekat.demo.exceptions.ThematicPropException;
import projekat.demo.exceptions.UserException;
import projekat.demo.model.ThematicProp;
import projekat.demo.model.ThematicPropDto;
import projekat.demo.service.ThematicPropService;

@RestController
@RequestMapping("/thematicprops")
public class ThematicPropController {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ThematicPropService thematicPropService;

	@PostMapping(consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createThematicProp(@RequestBody ThematicPropDto tp) {
		logger.info("> adding thematic prop");

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

	@DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ThematicPropException> deleteThematicProp(@RequestBody ThematicProp thematicProp) {
		logger.info(">> delete thematic prop");
		ThematicPropException pe = new ThematicPropException(thematicProp, "");

		if (!this.thematicPropService.deleteThematicProp(thematicProp)) {
			pe.setMessage("Thematic prop does not exist!");
			logger.info("<< delete thematic prop");
			return new ResponseEntity<ThematicPropException>(pe, HttpStatus.NOT_FOUND);
		} else {
			pe.setMessage("Deletion successfull!");
		}

		logger.info("<< delete thematic prop");
		return new ResponseEntity<ThematicPropException>(pe, HttpStatus.OK);
	}

}
