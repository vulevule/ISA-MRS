package projekat.demo.repository;

import org.springframework.data.repository.CrudRepository;

import projekat.demo.model.Place;

public interface PlaceRepository extends CrudRepository<Place, Long> {
	
	Place findByNameAndAddress(String name, String address);
}
