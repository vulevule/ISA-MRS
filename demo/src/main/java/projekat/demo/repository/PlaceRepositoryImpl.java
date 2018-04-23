package projekat.demo.repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import projekat.demo.model.Place;

@Repository
public class PlaceRepositoryImpl implements PlaceRepository {

	@Override
	public ArrayList<Place> findAll() {
		ArrayList<Place> places = new ArrayList<Place>();
		
		Place p = new Place("Cinestar");
		Place p1 = new Place("Vilin grad");
		
		places.add(p);
		places.add(p1);
		
		return places;
	}

}
