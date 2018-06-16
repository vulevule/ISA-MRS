package projekat.demo.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("System_admin")

public class Admin extends User{

	/**
	 * !!treba srediti da se ona odnosi na sistemskog admina!!
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean firstLogin;
	
}
