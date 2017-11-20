package com.rupesre.email;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

	final String senderEmailID = "usuario@gmail.com";
	final String senderPassword = "password";
	final String emailSMTPserver = "smtp.gmail.com";
	final String emailServerPort = "465";
	String receiverEmailID = null;
	static String emailSubject = "Test Mail";
	static String emailBody = " Test Body";

	public SendEmail(String receiverEmailID, String emailSubject, String emailBody) {
		this.receiverEmailID = receiverEmailID;

		Properties properties = new Properties();
		properties.put("mail.smtp.user", senderEmailID);
		properties.put("mail.smtp.host", emailSMTPserver);
		properties.put("mail.smtp.port", emailServerPort);
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.socketFactory.port", emailServerPort);
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.socketFactory.fallback", "false");

		SecurityManager security = System.getSecurityManager();

		try {
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(properties, auth);
			MimeMessage msg = new MimeMessage(session);

			msg.setText(emailBody);
			msg.setSubject(emailSubject);

			msg.setFrom(new InternetAddress(senderEmailID));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverEmailID));
			Transport.send(msg);
			System.out.println("Message Send");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	class SMTPAuthenticator extends javax.mail.Authenticator {
		public javax.mail.PasswordAuthentication getPasswordAuthentication() {
			return new javax.mail.PasswordAuthentication(senderEmailID, senderPassword);
		}
	}

	/**
	 * Antes de ejecutar la aplicacion es necesario entrar a esta dirección
	 * https://myaccount.google.com/lesssecureapps y permitir el acceso a
	 * aplicaciones menos seguras.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(":::: Start");
		SendEmail sendEmail = new SendEmail("ecm3198@gmail.com", "Correo con Java Mail Dos",
				"Este es un correo de prueba Dos");
		System.out.println(":::: Finish");
	}
}
