package projekat.demo.service;

import projekat.demo.dto.ArenaDto;
import projekat.demo.model.Arena;

public interface ArenaService {

	Iterable<Arena> findAllArenas();

	Arena createArena(ArenaDto arenaDto);

	Arena updateArena(Long arenaId, ArenaDto arenaDto);

	void deleteArena(Long arenaId);

}
