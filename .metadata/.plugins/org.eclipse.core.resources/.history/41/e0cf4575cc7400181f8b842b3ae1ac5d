package projekat.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import projekat.demo.dto.ReservationDTO;
import projekat.demo.dto.Seat;
import projekat.demo.exceptions.ExceptionReservation;
import projekat.demo.model.Reservation;
import projekat.demo.model.Term;
import projekat.demo.model.Visitor;
import projekat.demo.repository.ReservationRepository;
import projekat.demo.repository.TermRepository;
import projekat.demo.repository.UserRepository;

@Service
public class ReservationServiceImp implements ReservationService {

	@Autowired
	private UserRepository userRep;

	@Autowired
	private TermRepository termRepo;

	@Autowired
	private ReservationRepository resRepo;

	private ArrayList<Visitor> makeFriends(Set<String> inviteFriends) {
		//na osnovu email-a korisnika izvucemo Visitore
		ArrayList<Visitor> visitors = new ArrayList<Visitor>();
		Visitor v;
		Iterator<String> it = inviteFriends.iterator();
		while (it.hasNext()) {
			v = (Visitor)userRep.findByEmail(it.next().trim());
			visitors.add(v);
		}
		
		return visitors;
	}
	private ArrayList<Seat> makeSeats(Set<String> seats) {
		// TODO Auto-generated method stub
		ArrayList<Seat> allSeat = new ArrayList<Seat>();
		Iterator<String> it = seats.iterator();
		Seat s;
		while (it.hasNext()) {
			String[] tokens = it.next().trim().split("-");
			s = new Seat(Integer.parseInt(tokens[0].trim()), Integer.parseInt(tokens[1].trim()), true);
			allSeat.add(s);
		}

		return allSeat;
	}

	@Transactional(readOnly=false, propagation = Propagation.REQUIRES_NEW ,rollbackFor=Exception.class)
	@Override
	public ArrayList<Reservation> save(ReservationDTO r, Visitor v) throws ExceptionReservation {
		//izvucemo termin na osnovu id-a
		Term t = termRepo.findById(r.getTerm());
		
		// 1. prvo napravimo listu sedista od dobijenog seta
		ArrayList<Seat> allSelectedSeats = makeSeats(r.getSeats());
		ArrayList<Reservation> allReservation = new ArrayList<Reservation>();

		// 2. dobavimo sve prijatelje koje smo pozvali
		ArrayList<Visitor> allFriends = new ArrayList<Visitor>();
		if (r.getInviteFriends().isEmpty() == false) {
			allFriends = makeFriends(r.getInviteFriends());
			//3. sad prolazimo kroz listu prijatelja i dodeljujemo im sedista, odnosno pravimo rezervacije 
			for(int i = 0; i < allFriends.size(); i++){
				//prvo proverimo da li mozda vec postoji rezervacija za to sediste
				Reservation findRes =  this.resRepo.findByTermAndRowAndSeatNum(t,allSelectedSeats.get(i).getRow(), allSelectedSeats.get(i).getColumn());
				if(findRes != null){
					throw new ExceptionReservation("Seat is already reserved.");
				}
				Reservation res = new Reservation(allFriends.get(i),t, allSelectedSeats.get(i).getRow(), allSelectedSeats.get(i).getColumn());
				//setujemo da je sediste dodeljeno
				allSelectedSeats.get(i).setFree(false);
				//sacuvamo rezervaciju
				Reservation saveRes = resRepo.save(res);
				allReservation.add(saveRes);
			}
			//prolazimo kroz listu sedista trazimo slobodna i dodeljujemo ulogovanom korisniku
			for(int i = 0; i< allSelectedSeats.size(); i++){
				if(allSelectedSeats.get(i).isFree() == true){
					//opet uradimo istu proveru
					Reservation findRes =  this.resRepo.findByTermAndRowAndSeatNum(t,allSelectedSeats.get(i).getRow(), allSelectedSeats.get(i).getColumn());
					if(findRes != null){
						throw new ExceptionReservation("Seat is already reserved.");
					}
					Reservation rese = new Reservation(v, t, allSelectedSeats.get(i).getRow(), allSelectedSeats.get(i).getColumn());
					Reservation save = resRepo.save(rese);
					allReservation.add(save);
				}
			}
		}else{
			//ako nismo pozvali prijatelja onda sva sedista dodelimo ulogovanom korisniku
			for(int i = 0; i< allSelectedSeats.size(); i++){
				if(allSelectedSeats.get(i).isFree() == true){
					//i ovde izvrsimo istu proveru
					int row = allSelectedSeats.get(i).getRow();
					int colum = allSelectedSeats.get(i).getColumn();
					Reservation findRes =  this.resRepo.findByTermAndRowAndSeatNum(t,row, colum);
					if(findRes != null){
						throw new ExceptionReservation("Seat is already reserved.");
					}
					Reservation rese = new Reservation(v, t, allSelectedSeats.get(i).getRow(), allSelectedSeats.get(i).getColumn());
					Reservation save = resRepo.save(rese);
					allReservation.add(save);
				}
			}
		}
		
		
		return allReservation;
	}
	@Override
	public Iterable<Reservation> findReservationByUser(Visitor v) {
		// TODO Auto-generated method stub
		return resRepo.findByUser(v);
	}
	@Override
	public Iterable<Reservation> findReservationByTerm(long id) {
		// TODO Auto-generated method stub
		//1. izvucemo termin sa dobijenim id-om
		Term t = termRepo.findById(id);
		
		return resRepo.findByTerm(t);
	}
	@Override
	public Reservation cancelReservation(long id) {
		// pronadjemo trazenu rezervaciju pogladom vreme i onda vidimo da li moze brisanje ili ne
		Reservation findRes = resRepo.findById(id);
		
		LocalDateTime termin = LocalDateTime.of(findRes.getTerm().getProjectionDate(), findRes.getTerm().getProjectionTime());
		if ((termin.minusMinutes(30).compareTo(LocalDateTime.now()) < 0)){
			return null;
		}
		
		
		//brisemo rezervaciju
		this.resRepo.delete(findRes);	
		
		return findRes;
	}
	@Override
	public Reservation findResById(long id) {
		// TODO Auto-generated method stub
		return this.resRepo.findById(id);
	}

}
