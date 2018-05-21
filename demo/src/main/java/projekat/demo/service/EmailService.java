package projekat.demo.service;

import java.util.Random;

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
		String activateString = generateLoginActivateString();
		user.setActivateString(activateString);
			
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		helper.setTo(user.getEmail());
		helper.setText("<html> <body> Hi " + user.getName() + " " + user.getSurname() + ", \n\n Your activate string is " +
		activateString + ". You can activate acocunt by clicking on the <a href='http://localhost:8080/activate' id='id_link'> link </a> "
				+ " <script>  $('id_link').on('click', function(){"
				+ "	var activateAccount = {};"
				+ "activateAccount['email'] = " +  user.getEmail() + ";"
						+ "activateAccount['activateString'] = " + user.getActivateString() +";"+
				" $.ajax({"
				+ "type : 'POST',"
				+ "contentType : 'application/json,'"
				+ "dataType : 'json';"
				+ "data : JSON.stringify(activateAccount),"
				+ "success : function(data){"
				+ "var url = window.location.href;"				
				+ "window.location = url + '/index.html';"						
				+"});(</script>  "
				+ "</body> </html>", true);
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
