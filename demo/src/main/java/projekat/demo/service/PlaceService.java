package projekat.demo.service;

import projekat.demo.dto.PlaceDto;
import projekat.demo.model.Place;

public interface PlaceService {
	
	Iterable<Place> findAllPlaces();
	
	Place findPlaceByNameAndAddress(String name, String address);
	
	Place findPlaceById(long id);

	Place createPlace(PlaceDto placeDto);

	Place updatePlace(Long placeId, PlaceDto placeDto);

	void deletePlace(Long placeId);

}
