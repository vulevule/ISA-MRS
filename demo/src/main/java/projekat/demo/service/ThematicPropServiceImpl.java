package projekat.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekat.demo.model.Projection;
import projekat.demo.model.RoleType;
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
	public ThematicProp createThematicProp(String name, Long projectionId, String email) {
		Optional<Projection> foundProjection = projectionRepository.findById(projectionId);
		if (!foundProjection.isPresent()) {
			// TODO throw exception
		}
		Projection projection = foundProjection.get();
		Optional<User> foundUser = userRepository.findById(email);
		if (!foundUser.isPresent()) {
			// TODO throw exception
		}
		User user = userRepository.findById(email).get();
		
		if (user.getType() == RoleType.FAN_ZONE_ADMIN) {
			ThematicProp thematicProp = new ThematicProp(name, projection, user);
			return thematicPropRepository.save(thematicProp);
		}
		// TODO throw exception
		return null;
	}

	@Override
	public void deleteThematicProp(Long thematicPropId) {
		thematicPropRepository.deleteById(thematicPropId);

	}

}
