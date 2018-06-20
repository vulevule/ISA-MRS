package projekat.demo.repository;

import org.springframework.data.repository.CrudRepository;

import projekat.demo.model.Projection;
import projekat.demo.model.Term;

public interface TermRepository extends CrudRepository<Term, Long> {

	Iterable<Term> findByProjection(Projection p);
	
	Term findById(long id);
}
