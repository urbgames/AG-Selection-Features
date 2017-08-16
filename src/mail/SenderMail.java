package mail;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SenderMail {

	private Properties props = new Properties();
	private Session session;

	public enum Protocols {
		TLS, SSL;
	}

	public SenderMail(String username, String password, Protocols protocol) {
		session = Session.getDefaultInstance(getProperties(protocol), new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
//		session.setDebug(true);
	}

	private Properties getProperties(Protocols protocol) {
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		if (protocol == Protocols.TLS) {
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.port", "587");
		} else {
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.port", "465");
		}
		return props;
	}

	public void sendMail(InternetAddress to, InternetAddress[] toCC, String subject, String text, File[] file) {

		try {

			Message message = new MimeMessage(session);
			message.setRecipient(Message.RecipientType.TO, to);
			if (toCC != null)
				message.setRecipients(Message.RecipientType.CC, toCC);
			message.setSubject(subject);

			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(text);

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			if (file != null)
				addAttachment(multipart, file);
			message.setContent(multipart);

			Transport.send(message);
		

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	private void addAttachment(Multipart multipart, File[] file) {
		for (File file2 : file) {
			try {
				DataSource source = new FileDataSource(file2);
				BodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(file2.getName());
				multipart.addBodyPart(messageBodyPart);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}

}
