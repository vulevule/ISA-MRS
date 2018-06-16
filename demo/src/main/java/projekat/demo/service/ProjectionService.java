package projekat.demo.service;

import projekat.demo.model.Projection;

public interface ProjectionService {

	Projection createProjection(Projection p);
	
	Projection updateProjection(Projection p);
	
	boolean deleteProjection(Projection p);
	
	Iterable<Projection> findAll();

	Iterable<Projection> findAllByPlaceId(int id);
}
