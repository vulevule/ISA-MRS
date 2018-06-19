package projekat.demo.service;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import projekat.demo.model.Reservation;
import projekat.demo.model.Visitor;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Environment env;

	@Async
	public void sendNotification(Visitor user) throws MailException, InterruptedException, MessagingException {
		System.out.println("Sending email....");

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(user.getEmail());
		helper.setText("Hi " + user.getName() + " " + user.getSurname()
				+ ", \n\n  You can activate acocunt by clicking on the "
				+ "http://localhost:8080/users/activate?email=" + user.getEmail() 
				, true);
		helper.setSubject("Activate theaterize account");

		javaMailSender.send(message);

		System.out.println("Email is send");
	}

	
	public void sendReservation(ArrayList<Reservation> r, Visitor v) throws MessagingException {
		// slanje mejlova za rezervacije, ukoliko je rezervacija od visitoa onda on ne treba da potvrdi rez
		//prvo izdvojimo rezervacije ulogovanog korisnika 
		ArrayList<Reservation> loginUserRes = new ArrayList<Reservation>();
		ArrayList<Reservation> otherRes = new ArrayList<Reservation>();
		
		for(Reservation res : r){
			if(res.getUser().getEmail().equals(v.getEmail())){
				loginUserRes.add(res);
			}else{
				otherRes.add(res);
			}
		}
		
		sendMailLoginUser(loginUserRes, v);
		if(otherRes.size() > 0){
			sendOtherMail(otherRes, v);
		}
	}

	private void sendOtherMail(ArrayList<Reservation> otherRes, Visitor v) throws MessagingException {
		// TODO Auto-generated method stub
		System.out.println("Sending email....");
		Reservation r = otherRes.get(0);
		int size = otherRes.size();

		String seats = "";
		for(Reservation res : otherRes){
					
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
	
			helper.setTo(res.getUser().getEmail());
			helper.setText("Hi " + res.getUser().getName() + " " + res.getUser().getSurname()
					+ ", \n\n  Your friend " + v.getName() + " invited you to go and watch projection "
					+ res.getTerm().getProjection().getName() + " in " 
					+ res.getTerm().getProjection().getPlace().getName() + ". Your seat is row: "
					+ res.getRow() + " seat: " +res.getSeatNum()+ ". Please confirm the reservation on the link http://localhost:8080"	, true);
			helper.setSubject("Booking seats");
	
			javaMailSender.send(message);

		}
		System.out.println("Email is send");
		
	}


	@Async
	private void sendMailLoginUser(ArrayList<Reservation> loginUserRes, Visitor v) throws MessagingException {
		//uzmemo broj karata i izracunamo ukupnu cenu, ispisemo sva rezervisana sedista
		System.out.println("Sending email....");
		Reservation r = loginUserRes.get(0);
		int size = loginUserRes.size();

		String seats = "";
		for(Reservation res : loginUserRes){
			seats = seats + " row: " + res.getRow() + " seat: " + res.getSeatNum() + ",";
		}
		
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(v.getEmail());
		helper.setText("Hi " + v.getName() + " " + v.getSurname()
				+ ", \n\n  You can successfully booked  seats for projection "
				+ r.getTerm().getProjection().getName() + " in " 
				+ r.getTerm().getProjection().getPlace().getName() + ". Your seats are: "
				+ seats	, true);
		helper.setSubject("Booking seats");

		javaMailSender.send(message);

		System.out.println("Email is send");
		
		
	}
}
