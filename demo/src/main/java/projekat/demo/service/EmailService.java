package projekat.demo.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
	public void sendNotification(User user) throws MailException, InterruptedException {
		System.out.println("Sending email....");
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		String activateString = generateLoginActivateString();
		user.setActivateString(activateString);
		mail.setSubject("Activate account");
		mail.setText("Hello " + user.getName() + " " + user.getSurname() + ", \n\n Your activate string is "
				+ activateString + ". "); // uz ovo treba da se posalje i link ka stranici na kojoj ce se upisivate
											// aktivacioni string
		javaMailSender.send(mail);
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
