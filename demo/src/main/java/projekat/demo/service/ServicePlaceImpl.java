package projekat.demo.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import projekat.demo.model.Place;
import projekat.demo.repository.PlaceRepositoryImpl;

@Service
public class ServicePlaceImpl implements PlaceService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PlaceRepositoryImpl placeRepository;
	
	@Override
	public ArrayList<Place> findAll() {
		// TODO Auto-generated method stub
		logger.info("> findAll");
		ArrayList<Place> places = placeRepository.findAll();
		logger.info("< findAll");
		return places;
	}

}
