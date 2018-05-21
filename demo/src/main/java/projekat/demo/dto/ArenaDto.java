package projekat.demo.dto;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ArenaDto implements Serializable {

	private static final long serialVersionUID = -8119347015775021607L;

	private Long id;
	private String name;
	private int rowSeats;
	private int columnSeats;
	private Set<TermDto> terms;
	private Long placeId;

	@JsonCreator
	public ArenaDto(@JsonProperty("id") Long id, @JsonProperty("name") String name, @JsonProperty("rowSeats") int rowSeats,
			@JsonProperty("columnSeats") int columnSeats, @JsonProperty("terms") Set<TermDto> terms,
			@JsonProperty("placeId") Long placeId) {
		this.id = id;
		this.name = name;
		this.rowSeats = rowSeats;
		this.columnSeats = columnSeats;
		this.terms = terms;
		this.placeId = placeId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRowSeats() {
		return rowSeats;
	}

	public void setRowSeats(int rowSeats) {
		this.rowSeats = rowSeats;
	}

	public int getColumnSeats() {
		return columnSeats;
	}

	public void setColumnSeats(int columnSeats) {
		this.columnSeats = columnSeats;
	}

	public Set<TermDto> getTerms() {
		return terms;
	}

	public void setTerms(Set<TermDto> terms) {
		this.terms = terms;
	}

	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
	}

}
