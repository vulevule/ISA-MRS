package projekat.demo.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import projekat.demo.model.Place;

public interface PlaceRepository extends CrudRepository<Place, String> {

	Collection<Place> findAll();

	Place findByName(String name);
}
