package projekat.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import projekat.demo.dto.ReservationDTO;
import projekat.demo.exceptions.ReservationException;
import projekat.demo.model.Visitor;
import projekat.demo.service.ReservationService;

@RestController
public class ReservationController {
	
	@Autowired
	private ReservationService resService;
	
	public ResponseEntity<ReservationException> createReservation(HttpSession session, @RequestBody ReservationDTO reservation){
		//1. moramo proveriti broj pozvanih prijatelja i broj sedista
		if(reservation.getInviteFriends().size() + 1 >= reservation.getSeats().size()){
			//nedozvoliti rezervaciju
		}
		Visitor v = (Visitor)session.getAttribute("loginUser");
		//ovde napravimo rezervacije i listu rezervacija prosledimo servisu
		
		if(this.resService.save(reservation, v) == true){
		
		}
		
		
		return null;
		
	}
}
