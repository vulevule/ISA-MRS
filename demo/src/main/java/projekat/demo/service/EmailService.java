package projekat.demo.service;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import projekat.demo.model.User;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Environment env;

	@Async
	public void sendNotification(User user) throws MailException, InterruptedException, MessagingException {
		System.out.println("Sending email....");
		/*
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));*/
		String activateString = generateLoginActivateString();
		user.setActivateString(activateString);
		/*mail.setSubject("Activate account");
		
		
		
		mail.setText("Hello " + user.getName() + " " + user.getSurname() + ", \n\n Your activate string is "
				+ activateString + ". " + " <a href ='localhost:8080/users/activate'> Link to activate your account </a>"); // uz ovo treba da se posalje i link ka stranici na kojoj ce se upisivate
											// aktivacioni string
		javaMailSender.send(mail);*/
		
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		helper.setTo(user.getEmail());
		helper.setText("<html> <body> Hi " + user.getName() + " " + user.getSurname() + ", \n\n Your activate string is " +
		activateString + ". You can activate acocunt by clicking on the <a href='http://localhost:8080/activate.html'> link </a> </body> </html>", true);
		helper.setSubject("Activate theaterize account");
		
		
		javaMailSender.send(message);
		
		System.out.println("Email is send");
	}

	private String generateLoginActivateString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 18) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
	}
}
