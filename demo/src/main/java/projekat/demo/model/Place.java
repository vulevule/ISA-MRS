package projekat.demo.model;

public class Place {
	private String name;
	private String description;
	private Address address;
	private PlaceType type;

	public Place() {
	}

	public Place(String name, String description, Address address, PlaceType type) {
		this();
		this.name = name;
		this.description = description;
		this.address = address;
		this.type = type;
	}
	
	public Place(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public PlaceType getType() {
		return type;
	}

	public void setType(PlaceType type) {
		this.type = type;
	}

}
