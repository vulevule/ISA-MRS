package projekat.demo.dto;

public class Seat {
	private int row;
	private int column;
	private boolean free;

	public Seat() {
	}

	public Seat(int row, int column, boolean free) {
		this();
		this.row = row;
		this.column = column;
		this.free = free;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	
}
