package projekat.demo.controller;

import java.util.ArrayList;
import java.util.Iterator;

import javax.mail.MessagingException;
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
import projekat.demo.exceptions.LockedObject;
import projekat.demo.exceptions.ResException;
import projekat.demo.exceptions.ReservationException;
import projekat.demo.model.Reservation;
import projekat.demo.model.Visitor;
import projekat.demo.service.EmailService;
import projekat.demo.service.ReservationService;

@RestController
public class ReservationController {
	
	@Autowired
	private ReservationService resService;
	
	@Autowired
	private EmailService emailService;
	
	LockedObject object  = new LockedObject();
	
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
						
		Visitor v = (Visitor)session.getAttribute("loginUser");
		ArrayList<Reservation> allReservation = resService.save(reservation, v);
		ReservationException re = new ReservationException(allReservation, "");
		
		if (allReservation == null){
				re.setMessage("Can not reservation selected seats");
				return new ResponseEntity<ReservationException>(re, HttpStatus.BAD_REQUEST);
		}
		
		re.setMessage("Successful reservation");
		//kada prodju rezervacije treba poslati mejlove
		try {
			emailService.sendReservation(re.getR(), v);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="reservation/cancelReservation")
	public ResponseEntity<ResException> cancelReservation(@RequestParam("id") long id){
		logger.info(">> cancel reservation by id: " + id);
		
		Reservation cancel = this.resService.cancelReservation(id);
		
		ResException re =  new ResException(cancel, "");
		if(cancel == null){
			//nije moguce otkazati rezervaciju
			re.setMessage("Reservation can not been canceled");
			logger.info("<< cancel reservation");
			
			return new ResponseEntity<ResException>(re, HttpStatus.BAD_REQUEST);
		}
		
		
		re.setMessage("Reservation canceled");
		logger.info("<< cancel reservation");
		
		return new ResponseEntity<ResException>(re, HttpStatus.OK);
	}
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="reservationid")
	public ResponseEntity<Reservation> findById(@RequestParam("id") long id){
		Reservation r = this.resService.findResById(id);
		return new ResponseEntity<Reservation>(r, HttpStatus.OK);
	}
}
