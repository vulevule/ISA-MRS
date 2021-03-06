package projekat.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekat.demo.dto.ThematicPropDto;
import projekat.demo.exceptions.ProjectionException;
import projekat.demo.exceptions.ThematicPropException;
import projekat.demo.exceptions.UserException;
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
	public ThematicProp createThematicProp(ThematicPropDto thematicPropDto) {
		Optional<Projection> foundProjection = projectionRepository.findById(thematicPropDto.getProjectionId());
		if (!foundProjection.isPresent()) {
			throw new ProjectionException(null,
					"Projection with id " + thematicPropDto.getProjectionId() + "does not exist");
		}

		Optional<User> foundUser = userRepository.findById(thematicPropDto.getUserEmail());
		if (!foundUser.isPresent()) {
			throw new UserException(null, "User " + thematicPropDto.getUserEmail() + "does not exist");
		}

		if (foundUser.get().getRole() == RoleType.FAN_ZONE_ADMIN) {
			ThematicProp findThematicProp = this.thematicPropRepository
					.findByNameAndProjection(thematicPropDto.getName(), foundProjection.get());
			if (findThematicProp == null) {
				ThematicProp thematicProp = new ThematicProp();
				thematicProp.setName(thematicPropDto.getName());
				thematicProp.setProjection(foundProjection.get());
				thematicProp.setUser(foundUser.get());
				return this.thematicPropRepository.save(thematicProp);
			} else {
				throw new ThematicPropException(findThematicProp, "Thematic prop already exists");
			}
		} else {
			throw new UserException(foundUser.get(), "User is not fan zone admin");
		}
	}

	@Override
	public ThematicProp updateThematicProp(ThematicPropDto thematicPropDto) {

		Optional<Projection> foundProjection = projectionRepository.findById(thematicPropDto.getProjectionId());
		if (!foundProjection.isPresent()) {
			throw new ProjectionException(null,
					"Projection with id " + thematicPropDto.getProjectionId() + "does not exist");
		}

		Optional<User> foundUser = userRepository.findById(thematicPropDto.getUserEmail());
		if (!foundUser.isPresent()) {
			throw new UserException(null, "User " + thematicPropDto.getUserEmail() + "does not exist");
		}

		ThematicProp findThematicProp = this.thematicPropRepository.findByNameAndProjection(thematicPropDto.getName(),
				foundProjection.get());
		if(findThematicProp == null) {
			throw new ThematicPropException(findThematicProp, "Thematic prop does not exist");
		}
		else {
			return this.thematicPropRepository.save(findThematicProp);
		}
	}

	@Override
	public ThematicProp deleteThematicProp(ThematicPropDto thematicPropDto) {

		Optional<Projection> foundProjection = projectionRepository.findById(thematicPropDto.getProjectionId());
		if (!foundProjection.isPresent()) {
			throw new ProjectionException(null,
					"Projection with id " + thematicPropDto.getProjectionId() + "does not exist");
		}

		Optional<User> foundUser = userRepository.findById(thematicPropDto.getUserEmail());
		if (!foundUser.isPresent()) {
			throw new UserException(null, "User " + thematicPropDto.getUserEmail() + "does not exist");
		}

		ThematicProp findThematicProp = this.thematicPropRepository.findByNameAndProjection(thematicPropDto.getName(),
				foundProjection.get());

		if (findThematicProp == null) {
			throw new ThematicPropException(findThematicProp, "Thematic prop does not exist");
		} else {
			this.thematicPropRepository.delete(findThematicProp);
			return findThematicProp;
		}

	}

}
