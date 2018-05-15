package projekat.demo.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projekat.demo.exceptions.ProjectionException;
import projekat.demo.model.Projection;
import projekat.demo.service.ProjectionService;

@RestController
@RequestMapping("/projections")
public class ProjectionController {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProjectionService projectionService;

	@PostMapping(value = "/createProjection", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProjectionException> createProjection(@RequestBody Projection p) {
		logger.info("> adding projection");
		
		Projection createProjection = null;
		ProjectionException pe = new ProjectionException(createProjection, "");

		try {
			createProjection = projectionService.createProjection(p);
			if (createProjection == null) {
				pe.setMessage("Projection with same name and place already exists!");
				pe.setProjection(createProjection);
				return new ResponseEntity<ProjectionException>(pe, HttpStatus.ALREADY_REPORTED);
			}
			pe.setProjection(createProjection);
			pe.setMessage("Projection successfully created!");

		} catch (Exception e) {
			pe.setMessage(e.getMessage());
			return new ResponseEntity<ProjectionException>(pe, HttpStatus.EXPECTATION_FAILED);
		}

		logger.info("< adding projection");
		return new ResponseEntity<ProjectionException>(pe, HttpStatus.CREATED);
	}

	@PostMapping(value = "/updateProjection", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProjectionException> updateProjection(@RequestBody Projection projection) {
		logger.info(">> update projection");
		Projection updateProjection = this.projectionService.updateProjection(projection);
		ProjectionException pe = new ProjectionException(updateProjection, "");

		if (updateProjection == null) {
			pe.setMessage("Projection does not exist");
			logger.info("<< update projection");
			return new ResponseEntity<ProjectionException>(pe, HttpStatus.NOT_FOUND);
		} else {
			pe.setMessage("Projection update successfull!");
		}
		logger.info("<< update projection");
		return new ResponseEntity<ProjectionException>(pe, HttpStatus.OK);
	}

	@PostMapping(value = "/deleteProjection", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProjectionException> deleteProjection(@RequestBody Projection projection) {
		logger.info(">> delete projection");
		ProjectionException pe = new ProjectionException(projection, "");

		if (!this.projectionService.deleteProjection(projection)) {
			pe.setMessage("Projection does not exist!");
			logger.info("<< delete projection");
			return new ResponseEntity<ProjectionException>(pe, HttpStatus.NOT_FOUND);
		} else {
			pe.setMessage("Deletion successfull!");
		}

		logger.info("<< delete projection");
		return new ResponseEntity<ProjectionException>(pe, HttpStatus.OK);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Projection>> findAll() {
		logger.info(">> find all projections");
		
		Iterable<Projection> projections = projectionService.findAll();
		
		logger.info("<< find all projection");

		return new ResponseEntity<Iterable<Projection>>(projections, HttpStatus.OK);
	}
}
