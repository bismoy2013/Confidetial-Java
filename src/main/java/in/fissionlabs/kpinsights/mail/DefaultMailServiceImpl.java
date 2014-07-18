package in.fissionlabs.kpinsights.mail;

import in.fissionlabs.kpinsights.model.velocity.PasswordResetRequest;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

@Service("DefaultMailService")
public class DefaultMailServiceImpl implements DefaultMailService{


	private JavaMailSender mailSender;
	private SimpleMailMessage templateMessage;
	private VelocityEngine velocityEngine;
	private String passwordResetMailTemplateLocation;
	private String mailEncoding;


	@Override
	public Boolean sendPasswordResetMail(String toAddress, PasswordResetRequest request)  {
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			Map<String,Object> model = new HashMap<String,Object>();
			model.put("request", request);
			String emailBody = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,passwordResetMailTemplateLocation,mailEncoding, model);
			helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
			mimeMessage.setContent(emailBody, "text/html");
			helper.setTo(toAddress);
			helper.setSubject("Password reset information.");
			this.mailSender.send(mimeMessage);
			return true;
		} catch (MessagingException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public Boolean sendConfirmationMail(String toAddress, String body){
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			String emailBody = "<h4>" + body +"</h4>";
			helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
			mimeMessage.setContent(emailBody, "text/html");
			helper.setTo(toAddress);
			helper.setSubject("Password successfully reset");
			this.mailSender.send(mimeMessage);
			return true;
		} catch (MessagingException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	public JavaMailSender getMailSender() {
		return mailSender;
	}


	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}


	public SimpleMailMessage getTemplateMessage() {
		return templateMessage;
	}


	public void setTemplateMessage(SimpleMailMessage templateMessage) {
		this.templateMessage = templateMessage;
	}


	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}


	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}


	public String getMailEncoding() {
		return mailEncoding;
	}


	public void setMailEncoding(String mailEncoding) {
		this.mailEncoding = mailEncoding;
	}


	public String getPasswordResetMailTemplateLocation() {
		return passwordResetMailTemplateLocation;
	}


	public void setPasswordResetMailTemplateLocation(
			String passwordResetMailTemplateLocation) {
		this.passwordResetMailTemplateLocation = passwordResetMailTemplateLocation;
	}

}
