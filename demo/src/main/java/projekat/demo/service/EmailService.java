package projekat.demo.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

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
}
