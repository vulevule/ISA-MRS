package projekat.demo.service;

import java.sql.Time;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekat.demo.dto.ReservationDTO;
import projekat.demo.dto.Seat;
import projekat.demo.exceptions.ReservationException;
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

	@Override
	public ArrayList<Reservation> save(ReservationDTO r, Visitor v) {
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
					Reservation rese = new Reservation(v, t, allSelectedSeats.get(i).getRow(), allSelectedSeats.get(i).getColumn());
					Reservation save = resRepo.save(rese);
					allReservation.add(save);
				}
			}
		}else{
			//ako nismo pozvali prijatelja onda sva sedista dodelimo ulogovanom korisniku
			for(int i = 0; i< allSelectedSeats.size(); i++){
				if(allSelectedSeats.get(i).isFree() == true){
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
	public ReservationException cancelReservation(long id) {
		// pronadjemo trazenu rezervaciju pogladom vreme i onda vidimo da li moze brisanje ili ne
		Reservation findRes = resRepo.findById(id);
		
		ZonedDateTime currentTime = ZonedDateTime.now();
		
		Date resDate = findRes.getTerm().getProjectionDate();
		Time resTime = findRes.getTerm().getProjectionTime();
		
		//treba spojiti ovo vreme i datum i onda da nadjemo razliku izmedju trenutnog vremena i vremena projekcije
		
		
		
		
		
		
		return null;
	}

}
