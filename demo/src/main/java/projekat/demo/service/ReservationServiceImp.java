package projekat.demo.service;


import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import projekat.demo.dto.ReservationDTO;
import projekat.demo.model.Visitor;

@Service
public class ReservationServiceImp implements ReservationService {

	@Override
	@Transactional(readOnly = false)
	public boolean save(ReservationDTO r, Visitor v) {
		// prolazimo kroz listu prijatelja i listu sedista i pravimo rezervacije 
		//sva ostala sedista koja ostanu idu za visitora
		
		return false;
	}

}
