package projekat.demo.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectionDto implements Serializable {

	private static final long serialVersionUID = 1781494581374828651L;
	
	private Long id;

	@JsonCreator
	public ProjectionDto(@JsonProperty("id") Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
