package projekat.demo.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ThematicPropDto implements Serializable {

	private static final long serialVersionUID = 4151573907787468526L;
	
	private String name;
	private Long projectionId;
	private String userEmail;

	@JsonCreator
	public ThematicPropDto(@JsonProperty("name") String name, 
			@JsonProperty("projectionId") Long projectionId, 
			@JsonProperty("userEmail") String userEmail) {
		this.name = name;
		this.projectionId = projectionId;
		this.userEmail = userEmail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getProjectionId() {
		return projectionId;
	}

	public void setProjectionId(Long projectionId) {
		this.projectionId = projectionId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

}
