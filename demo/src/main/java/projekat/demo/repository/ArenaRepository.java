package projekat.demo.repository;

import org.springframework.data.repository.CrudRepository;

import projekat.demo.model.Arena;
import projekat.demo.model.Place;

public interface ArenaRepository extends CrudRepository<Arena, String>{
	
	Arena findByNameAndPlace(String name, Place place);

}
