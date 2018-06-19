package projekat.demo.controller;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import projekat.demo.dto.ReservationDTO;
import projekat.demo.exceptions.ReservationException;
import projekat.demo.model.Reservation;
import projekat.demo.model.Term;
import projekat.demo.model.Visitor;
import projekat.demo.service.ReservationService;

@RestController
public class ReservationController {
	
	@Autowired
	private ReservationService resService;
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

	@PostMapping(value = "reservation/createReservation", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReservationException> createReservation(HttpSession session, @RequestBody ReservationDTO reservation){
		//1. moramo proveriti broj pozvanih prijatelja i broj sedista
		logger.info("reservation");
		logger.info("term id: " + reservation.getTerm());

		Iterator<String> it1 = reservation.getInviteFriends().iterator();
		while(it1.hasNext()){
			logger.info("friends: " + it1.next());
		}
		
	    Iterator<String> it = reservation.getSeats().iterator();
	    while(it.hasNext()){
	    	logger.info("seats: " + it.next());
	    }
		/*
		 * slanje podataka radi, sad jos samo sve to lepo obraditi u servisu
		 */
				
		Visitor v = (Visitor)session.getAttribute("loginUser");
		//ovde napravimo rezervacije i listu rezervacija prosledimo servisu
		
		
		ArrayList<Reservation> allReservation = resService.save(reservation, v);
		
		ReservationException re = new ReservationException(allReservation,"Successful reservation");
		if(allReservation == null){
			re.setMessage("Can not reservation selected seats");
			return new ResponseEntity<ReservationException>(re, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<ReservationException>(re, HttpStatus.CREATED);
		
	}
	
	//sve rezervacije ulogovanog korisnika
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="reservation")
	public ResponseEntity<Iterable<Reservation>> allReservation(HttpSession session) {
		logger.info(">> find all reservation for login user");
		
		
		Visitor v = (Visitor)session.getAttribute("loginUser");
		Iterable<Reservation> reservations  = resService.findReservationByUser(v);
		
		Iterator<Reservation> it = reservations.iterator();
		while(it.hasNext()){
			logger.info("Term id: " + it.next().getTerm().getId());
		}
		 
		logger.info("<< find all reservation for login user");

		return new ResponseEntity<Iterable<Reservation>>(reservations, HttpStatus.OK);
	}
	//sve rezervacije za odredjeni termin
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="reservationByTerm")
	public ResponseEntity<Iterable<Reservation>> allReservationByTerm(@RequestParam("id") long id) {
		logger.info(">> find all reservation by term: " + id);
		
		Iterable<Reservation> reservations  = resService.findReservationByTerm(id);
		
		logger.info("<< find all reservation by term");

		return new ResponseEntity<Iterable<Reservation>>(reservations, HttpStatus.OK);
	}
}
