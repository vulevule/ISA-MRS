package projekat.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import projekat.demo.model.Projection;
import projekat.demo.model.ThematicProp;
import projekat.demo.model.User;

public interface ThematicPropRepository extends CrudRepository<ThematicProp, Long> {

	List<ThematicProp> findByUser(User user);

	List<ThematicProp> findByProjection(Projection projection);

	ThematicProp findByNameAndProjection(String name, Projection projection);

}