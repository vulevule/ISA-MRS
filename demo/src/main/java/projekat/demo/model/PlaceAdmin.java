package projekat.demo.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("Theater_place_admin")
public class PlaceAdmin extends Admin {

	/**
	 * 
	 */
	

}
