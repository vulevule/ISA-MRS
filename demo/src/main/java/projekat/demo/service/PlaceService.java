package projekat.demo.service;

import projekat.demo.model.Arena;
import projekat.demo.model.Place;

public interface PlaceService {

	Place createPlace(Place p);
	
	Place updatePlace(Place p);
	
	boolean deletePlace(Place p);
	
	Arena createArena(Arena a);
	
	Arena updateArena(Arena a);
	
	boolean deleteArena(Arena a);
	
}
