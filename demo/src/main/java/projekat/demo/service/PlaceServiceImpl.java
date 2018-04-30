package projekat.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekat.demo.model.Place;
import projekat.demo.repository.PlaceRepository;

@Service
public class PlaceServiceImpl implements PlaceService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PlaceRepository placeRepository;

	@Override
	public Place createPlace(Place p) {
		// treba navesti da nijedan atribut ne sme biti null

		Place findPlace = this.placeRepository.findByName(p.getName());
		if (findPlace == null) {
			return this.placeRepository.save(p);
		}
		return null;
	}

}
