package projekat.demo.service;

import java.util.ArrayList;

import projekat.demo.dto.ReservationDTO;
import projekat.demo.exceptions.ReservationException;
import projekat.demo.model.Reservation;
import projekat.demo.model.Visitor;

public interface ReservationService {
	
	public ArrayList<Reservation> save(ReservationDTO r, Visitor v);

	public Iterable<Reservation> findReservationByUser(Visitor v);

	public Iterable<Reservation> findReservationByTerm(long id);

	public ReservationException cancelReservation(long id);

}
