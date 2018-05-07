package projekat.demo.service;

import projekat.demo.model.ThematicProp;

public interface ThematicPropService {
	
	ThematicProp createThematicProp(String name, Long projectionId, String email);
	
	void deleteThematicProp(Long thematicPropId);
	

}
