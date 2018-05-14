package projekat.demo.service;

import java.util.Collection;

import projekat.demo.model.Arena;
import projekat.demo.model.Place;

public interface PlaceService {
	
	Collection<Place> findAll();

	Place createPlace(Place p);
	
	Place updatePlace(Place p);
	
	boolean deletePlace(Place p);
	
	Collection<Arena> findAllArenas();
	
	Arena createArena(Arena a);
	
	Arena updateArena(Arena a);
	
	boolean deleteArena(Arena a);
	
}
