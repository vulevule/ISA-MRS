package projekat.demo.repository;

import org.springframework.data.repository.CrudRepository;

import projekat.demo.model.Reservation;
import projekat.demo.model.Term;
import projekat.demo.model.Visitor;

public interface ReservationRepository extends CrudRepository<Reservation, Long>{

	Iterable<Reservation> findByUser(Visitor v);

	Iterable<Reservation> findByTerm(Term t);
	
	Reservation findById(long id);
	

}
