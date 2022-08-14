package sg.edu.iss.kuruma.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.kuruma.model.User;
import sg.edu.iss.kuruma.repository.UserRepository;



@Service
public class EmailNotificationService {
	@Autowired
	UserRepository urepo;
	@Autowired
	private JavaMailSender sender;
	
	public void sentEmailNotification(User user) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(user.getEmail());
		msg.setSubject("testing");
		msg.setText("testing testing");
		sender.send(msg);
	}
	
	 
	private void sendVerificationEmail(User user, String siteURL) throws UnsupportedEncodingException, MessagingException {
	    String toAddress = user.getEmail();
	    String fromAddress = "Your email address";
	    String senderName = "Your company name";
	    String subject = "Please verify your registration";
	    String content = "Dear [[name]],<br>"
	            + "Please click the link below to verify your registration:<br>"
	            + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
	            + "Thank you,<br>"
	            + "Your company name.";
	     
	    MimeMessage message = sender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setFrom(fromAddress, senderName);
	    helper.setTo(toAddress);
	    helper.setSubject(subject);
	     
	    content = content.replace("[[name]]", user.getUsername());
	    String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();
	     
	    content = content.replace("[[URL]]", verifyURL);
	     
	    helper.setText(content, true);
	     
	    sender.send(message);
    }

}
