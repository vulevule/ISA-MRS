package projekat.demo.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Fan_zone_admin")
public class FanZoneAdmin extends Admin {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
