package projekat.demo.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projekat.demo.exceptions.ThematicPropException;
import projekat.demo.model.ThematicProp;
import projekat.demo.service.ThematicPropService;

@RestController
@RequestMapping("/thematicprops")
public class ThematicPropController {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ThematicPropService thematicPropService;

	@PostMapping(value = "/createThematicProp", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ThematicPropException> createThematicProp(@RequestBody ThematicProp tp) {
		logger.info("> adding thematic prop");

		ThematicProp createThematicProp = null;
		ThematicPropException pe = new ThematicPropException(createThematicProp, "");

		try {
			createThematicProp = thematicPropService.createThematicProp(tp);
			if (createThematicProp == null) {
				pe.setMessage("Thematic Prop with same name and place already exists!");
				pe.setThematicProp(createThematicProp);
				return new ResponseEntity<ThematicPropException>(pe, HttpStatus.ALREADY_REPORTED);
			}
			pe.setThematicProp(createThematicProp);
			pe.setMessage("Thematic Prop successfully created!");

		} catch (Exception e) {
			pe.setMessage(e.getMessage());
			return new ResponseEntity<ThematicPropException>(pe, HttpStatus.EXPECTATION_FAILED);
		}

		logger.info("< adding thematic prop");
		return new ResponseEntity<ThematicPropException>(pe, HttpStatus.CREATED);
	}

	@PostMapping(value = "/deleteThematicProp", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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
