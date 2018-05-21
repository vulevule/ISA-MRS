package projekat.demo.exceptions;

import projekat.demo.model.Arena;

public class ArenaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Arena arena;
	private String message;

	public ArenaException(String message) {
		super(message);
	}

	public ArenaException(Arena arena, String message) {
		super(message);
		this.arena = arena;
	}

	public Arena getArena() {
		return arena;
	}

	public void setArena(Arena arena) {
		this.arena = arena;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
