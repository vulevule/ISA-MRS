package projekat.demo.service;

import projekat.demo.dto.ThematicPropDto;
import projekat.demo.model.ThematicProp;

public interface ThematicPropService {
	
	ThematicProp createThematicProp(ThematicPropDto thematicPropDto);
	
	ThematicProp updateThematicProp(ThematicPropDto thematicPropDto);
	
	ThematicProp deleteThematicProp(ThematicPropDto thematicPropDto);

}
