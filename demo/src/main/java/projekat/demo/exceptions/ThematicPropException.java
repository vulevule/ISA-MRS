package projekat.demo.exceptions;

import projekat.demo.model.ThematicProp;

public class ThematicPropException {

	private ThematicProp thematicProp;
	private String message;

	public ThematicPropException() {
	}

	public ThematicPropException(ThematicProp thematicProp, String message) {
		this();
		this.thematicProp = thematicProp;
		this.message = message;
	}

	public ThematicProp getThematicProp() {
		return thematicProp;
	}

	public void setThematicProp(ThematicProp thematicProp) {
		this.thematicProp = thematicProp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
