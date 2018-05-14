package projekat.demo.service;

import projekat.demo.model.ThematicProp;
import projekat.demo.model.ThematicPropDto;

public interface ThematicPropService {
	
	ThematicProp createThematicProp(ThematicPropDto thematicPropDto);
	
	ThematicProp updateThematicProp(ThematicProp thematicProp);
	
	boolean deleteThematicProp(ThematicProp thematicProp);

}
