package projekat.demo.model;

public class ArenaException {

	private Arena arena;
	private String message;
	
	public ArenaException() {}

	public ArenaException(Arena arena, String message) {
		this();
		this.arena = arena;
		this.message = message;
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