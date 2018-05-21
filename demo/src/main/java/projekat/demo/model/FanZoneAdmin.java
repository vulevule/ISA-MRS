package projekat.demo.model;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("Fan_zone_admin")
public class FanZoneAdmin extends Admin {
	/**
	 * 
	 */


}
