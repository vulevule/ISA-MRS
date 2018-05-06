package projekat.demo.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import projekat.demo.model.Place;
import projekat.demo.model.Projection;

public interface ProjectionRepository extends CrudRepository<Projection, String>{

	ArrayList<Projection> findAll();
	
	Projection findByNameAndPlace(String name, Place place);
	
}
