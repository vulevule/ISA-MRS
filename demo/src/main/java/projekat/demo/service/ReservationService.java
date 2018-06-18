package projekat.demo.service;

import projekat.demo.dto.ReservationDTO;
import projekat.demo.model.Visitor;

public interface ReservationService {
	
	public boolean save(ReservationDTO r, Visitor v);

}
