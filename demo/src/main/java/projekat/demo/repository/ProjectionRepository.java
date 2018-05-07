package projekat.demo.repository;

import org.springframework.data.repository.CrudRepository;

import projekat.demo.model.Place;
import projekat.demo.model.Projection;

public interface ProjectionRepository extends CrudRepository<Projection, Long>{
	
	Projection findByNameAndPlace(String name, Place place);
	
}
