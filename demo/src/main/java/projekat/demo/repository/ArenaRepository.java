package projekat.demo.repository;

import org.springframework.data.repository.CrudRepository;

import projekat.demo.model.Arena;
import projekat.demo.model.Place;

public interface ArenaRepository extends CrudRepository<Arena, Long> {

	Arena findByNameAndPlace(String name, Place place);
	
	Arena findById(long id);
	
	Iterable<Arena> findByPlace(Place p);

}
