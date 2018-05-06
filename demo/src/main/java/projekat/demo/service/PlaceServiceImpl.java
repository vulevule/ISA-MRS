package projekat.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekat.demo.model.Arena;
import projekat.demo.model.Place;
import projekat.demo.repository.ArenaRepository;
import projekat.demo.repository.PlaceRepository;

@Service
public class PlaceServiceImpl implements PlaceService {

	

	@Autowired
	private PlaceRepository placeRepository;
	
	@Autowired
	private ArenaRepository arenaRepository;
	

	@Override
	public Place createPlace(Place p) {
		// treba navesti da nijedan atribut ne sme biti null

		Place findPlace = this.placeRepository.findByName(p.getName());
		if (findPlace == null) {
			return this.placeRepository.save(p);
		}
		return null;
	}

	@Override
	public Place updatePlace(Place p) {		
		
		Place searchPlace = this.placeRepository.findByName(p.getName());
		if (searchPlace == null) {
			return null;
		}
		
		return this.placeRepository.save(p);
	}

	@Override
	public boolean deletePlace(Place p) {
		Place searchPlace = this.placeRepository.findByName(p.getName());
		if (searchPlace == null) {
			return false;
		}
		
		this.placeRepository.delete(p);
		
		return true;
	}

	@Override
	public Arena createArena(Arena a) {
		Arena searchArena = this.arenaRepository.findByNameAndPlace(a.getName(), a.getPlace());
		if (searchArena == null) {
			return this.arenaRepository.save(a);
		}
		
		return null;
	}

	@Override
	public Arena updateArena(Arena a) {
		Arena searchArena = this.arenaRepository.findByNameAndPlace(a.getName(), a.getPlace());
		if (searchArena == null) {
			return null;
		}
		
		return this.arenaRepository.save(a);
	}

	@Override
	public boolean deleteArena(Arena a) {
		Arena searchArena = this.arenaRepository.findByNameAndPlace(a.getName(), a.getPlace());
		if (searchArena == null) {
			return false;
		}
		
		this.arenaRepository.delete(a);
		
		return true;
	}

}
