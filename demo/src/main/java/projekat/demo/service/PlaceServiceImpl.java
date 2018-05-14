package projekat.demo.service;

import java.util.Collection;

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
	public Collection<Place> findAll() {
		return placeRepository.findAll();
	}

	@Override
	public Place createPlace(Place p) {
		// treba navesti da nijedan atribut ne sme biti null

		Place findPlace = this.placeRepository.findByNameAndAddress(p.getName(), p.getAddress());
		if (findPlace == null) {
			return this.placeRepository.save(p);
		}
		return null;
	}

	@Override
	public Place updatePlace(Place p) {
		Place searchPlace = this.placeRepository.findByNameAndAddress(p.getName(), p.getAddress());

		if (searchPlace == null) {
			return null;
		}
		p.setId(searchPlace.getId());
		return this.placeRepository.save(p);
	}

	@Override
	public boolean deletePlace(Place p) {
		Place searchPlace = this.placeRepository.findByNameAndAddress(p.getName(), p.getAddress());
		if (searchPlace == null) {
			return false;
		}

		this.placeRepository.delete(p);

		return true;
	}

	@Override
	public Collection<Arena> findAllArenas() {
		Collection<Arena> arenas = this.arenaRepository.findAll();

		return arenas;
	}

	@Override
	public Arena createArena(Arena a) {
		Place searchPlace = this.placeRepository.findByNameAndAddress(a.getPlace().getName(),
				a.getPlace().getAddress());
		if (searchPlace == null) {
			return null;
		}
		if (searchPlace != a.getPlace()) {
			System.out.println("***** FATAL ERROR ***** - PLACES IN ARENA ARE NOT EQUAL!!!");
		}
		a.setPlace(searchPlace);

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
