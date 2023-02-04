package com.example.demo;
import com.example.demo.Helper_Class.Window_Notification_System;

import java.awt.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

//////////////////////////////////////////////This is a test Class

public class SendEmail extends Authenticator
{
	static ArrayList<String> Users = new ArrayList<>();
	static String SubjectText = "Subject Text";
	static String EmailText = "Email Text";
	static Boolean WindowsNotificationBool = true;

	public static void main(String [] args) throws NoSuchProviderException, AWTException
	{
		Users.add("raoofagh@gmail.com");
		final String emailSMTPserver = "smtp.gmail.com";
		final String emailSMTPPort = "465";

		// Assuming you are sending email from localhost
		String host = "smtp.gmail.com";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.host", emailSMTPserver);
		properties.put("mail.smtp.socketFactory.port", emailSMTPPort);
		properties.put("mail.smtp.socketFactory.class",
		               "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", emailSMTPPort);

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties,
		                                             new Authenticator()
		                                             {
			                                             protected PasswordAuthentication getPasswordAuthentication()
			                                             {
				                                             return new PasswordAuthentication(
						                                             "testemailsender772@gmail.com", "zdbhrhyzrbhgtjlv");
			                                             }
		                                             });

		javax.mail.Transport transport = session.getTransport("smtp");

		session.setDebug(true);
		for (String user: Users)
		{
			try {
				InternetAddress addressFrom = new InternetAddress("testemailsender772@gmail.com");

				MimeMessage message = new MimeMessage(session);
				message.setSender(addressFrom);
				message.setSubject(SubjectText);
				message.setContent(EmailText, "text/plain");
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(user));

				transport.connect();
				transport.send(message);
				transport.close();
			} catch (MessagingException mex) {
				mex.printStackTrace();
			}
		}
		if (WindowsNotificationBool)
		{
			Window_Notification_System notification_system = new Window_Notification_System();
		}
		System.exit(0);
	}
}