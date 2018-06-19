package projekat.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekat.demo.dto.ArenaDto;
import projekat.demo.dto.TermDto;
import projekat.demo.exceptions.ArenaException;
import projekat.demo.exceptions.PlaceException;
import projekat.demo.exceptions.TermException;
import projekat.demo.model.Arena;
import projekat.demo.model.Place;
import projekat.demo.model.Term;
import projekat.demo.repository.ArenaRepository;
import projekat.demo.repository.PlaceRepository;
import projekat.demo.repository.TermRepository;

@Service
public class ArenaServiceImpl implements ArenaService {

	@Autowired
	private PlaceRepository placeRepository;

	@Autowired
	private ArenaRepository arenaRepository;

	@Autowired
	private TermRepository termRepository;

	@Override
	public Iterable<Arena> findAllArenas() {
		return this.arenaRepository.findAll();
	}

	@Override
	public Arena createArena(ArenaDto arenaDto) {

		Optional<Place> foundPlace = placeRepository.findById(arenaDto.getPlaceId());
		if (!foundPlace.isPresent()) {
			throw new PlaceException(null, "Place with id " + arenaDto.getPlaceId() + "does not exist");
		}

		for (TermDto term : arenaDto.getTerms()) {
			Optional<Term> foundTerm = termRepository.findById(term.getId());
			if (!foundTerm.isPresent()) {
				throw new TermException(null, "Term with id " + term.getId() + "does not exist");
			}
		}

		Arena findArena = this.arenaRepository.findByNameAndPlace(arenaDto.getName(), foundPlace.get());
		if (findArena == null) {
			Arena arena = new Arena();
			arena.setName(arenaDto.getName());
			arena.setPlace(foundPlace.get());
			
			Collection<Long> termsIds = new ArrayList<>();
			for (TermDto termDto : arenaDto.getTerms()) {
				termsIds.add(termDto.getId());
				
			}
			Set<Term> allTerms = (Set<Term>) termRepository.findAllById(termsIds);
			arena.setTerms(allTerms);
			
			arena.setColumnSeats(arenaDto.getColumnSeats());
			arena.setRowSeats(arenaDto.getRowSeats());
			return this.arenaRepository.save(arena);
		} else {
			throw new ArenaException(findArena, "Arena already exists");
		}

		/*
		 * Place searchPlace =
		 * this.placeRepository.findByNameAndAddress(a.getPlace().getName(),
		 * a.getPlace().getAddress()); if (searchPlace == null) { return null; } if
		 * (searchPlace != a.getPlace()) { System.out.
		 * println("***** FATAL ERROR ***** - PLACES IN ARENA ARE NOT EQUAL!!!"); }
		 * a.setPlace(searchPlace);
		 * 
		 * Arena searchArena = this.arenaRepository.findByNameAndPlace(a.getName(),
		 * a.getPlace()); if (searchArena == null) { return
		 * this.arenaRepository.save(a); }
		 * 
		 * return null;
		 */
	}

	@Override
	public Arena updateArena(Long arenaId, ArenaDto arenaDto) {

		Optional<Place> foundPlace = placeRepository.findById(arenaDto.getPlaceId());
		if (!foundPlace.isPresent()) {
			throw new PlaceException(null, "Place with id " + arenaDto.getPlaceId() + "does not exist");
		}

		for (TermDto term : arenaDto.getTerms()) {
			Optional<Term> foundTerm = termRepository.findById(term.getId());
			if (!foundTerm.isPresent()) {
				throw new TermException(null, "Term with id " + term.getId() + "does not exist");
			}
		}

		Optional<Arena> foundArena = this.arenaRepository.findById(arenaId);
		if (!foundArena.isPresent()) {
			throw new ArenaException("Arena does not exist");
		} else {
			Arena arena = foundArena.get();
			arena.setName(arenaDto.getName());
			arena.setPlace(foundPlace.get());
			arena.setRowSeats(arenaDto.getRowSeats());
			arena.setColumnSeats(arenaDto.getColumnSeats());
			
			Collection<Long> termsIds = new ArrayList<>();
			for (TermDto termDto : arenaDto.getTerms()) {
				termsIds.add(termDto.getId());
				
			}
			Set<Term> allTerms = (Set<Term>) termRepository.findAllById(termsIds);
			arena.setTerms(allTerms);
			return this.arenaRepository.save(arena);
		}
	}

	@Override
	public void deleteArena(Long arenaId) {
		arenaRepository.deleteById(arenaId);

	}
}
