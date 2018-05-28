package projekat.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekat.demo.model.FanZoneAdmin;
import projekat.demo.model.Projection;
import projekat.demo.model.ThematicProp;
import projekat.demo.model.User;
import projekat.demo.repository.ProjectionRepository;
import projekat.demo.repository.ThematicPropRepository;
import projekat.demo.repository.UserRepository;

@Service
public class ThematicPropServiceImpl implements ThematicPropService {

	@Autowired
	private ThematicPropRepository thematicPropRepository;

	@Autowired
	private ProjectionRepository projectionRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public ThematicProp createThematicProp(ThematicProp thematicProp) {
		Optional<Projection> foundProjection = projectionRepository.findById(thematicProp.getId());
		if (!foundProjection.isPresent()) {
			// TODO throw exception
		}
	
		Optional<User> foundUser = userRepository.findById(thematicProp.getUser().getEmail());
		if (!foundUser.isPresent()) {
			// TODO throw exception
		}
		User user = userRepository.findById(thematicProp.getUser().getEmail()).get();
		
		if (user instanceof FanZoneAdmin) {
			ThematicProp findThematicProp = this.thematicPropRepository.findByNameAndProjection(thematicProp.getName(), thematicProp.getProjection());
			if (findThematicProp == null) {
				return this.thematicPropRepository.save(thematicProp);
			}
		}
		// TODO throw exception
		return null;
	}
	
	@Override
	public ThematicProp updateThematicProp(ThematicProp thematicProp) {
		
		return this.thematicPropRepository.save(thematicProp);
	}

	@Override
	public boolean deleteThematicProp(ThematicProp thematicProp) {
		
		if (thematicProp == null) {
			return false;
		}
		ThematicProp deleteThematicProp = this.thematicPropRepository.findByNameAndProjection(thematicProp.getName(), thematicProp.getProjection());
		
		if (deleteThematicProp == null) {
			return false;
		}
	
		this.thematicPropRepository.delete(thematicProp);
		
		return true;

	}

}
