package projekat.demo.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import projekat.demo.model.Place;

public interface PlaceRepository extends CrudRepository<Place, String> {
	
	ArrayList<Place> findAll();
	
	Place findByName(String name);
}
