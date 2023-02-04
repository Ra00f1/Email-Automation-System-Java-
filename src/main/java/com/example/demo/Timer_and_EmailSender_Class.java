package com.example.demo;

import com.example.demo.Helper_Class.Window_Notification_System;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.*;
import java.util.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Timer_and_EmailSender_Class
{
	List EmailInfo = new ArrayList();
	List Emails = new ArrayList();
	Boolean WindowsNotificationBool;

	public Timer_and_EmailSender_Class(List Emails,List EmailInfo, Boolean WindowsNotificationBool)
	{
		//setEmailInfo(EmailInfo);
		//setEmails(Emails);
		//setWindowsNotificationBool(WindowsNotificationBool);

		this.EmailInfo = EmailInfo;
		this.Emails = Emails;
		this.WindowsNotificationBool = WindowsNotificationBool;
		Start();
	}

	public void Start()
	{
		System.out.println(EmailInfo);
		System.out.println(Emails);
		int year = (int) EmailInfo.get(0);
		int month = (int) EmailInfo.get(2) - 1;
		int day = (int) EmailInfo.get(1);
		int hour = (int) EmailInfo.get(3);
		int minute = (int) EmailInfo.get(4);
		System.out.println(month);

		// Set the date and time at which the task is to be executed
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, 20);

		Date startTime = calendar.getTime();

		// Create a Timer and schedule the task to run at the specified date and time
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				try
				{
					EmailSender(Emails, (String) EmailInfo.get(5), (String) EmailInfo.get(6));
				} catch (NoSuchProviderException e)
				{
					throw new RuntimeException(e);
				} catch (AWTException e)
				{
					throw new RuntimeException(e);
				}
			}
		}, startTime);
	}
	public void EmailSender(List<String> Users, String SubjectText, String EmailText) throws NoSuchProviderException, AWTException
	{
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
	}

	///////////////////////////////////////////////Gets and Sets///////////////////////////////////////////////////////////

	public void setEmailInfo(List emailInfo)
	{
		this.EmailInfo = emailInfo;
	}

	public void setEmails(List emails)
	{
		this.Emails = emails;
	}

	public void setWindowsNotificationBool(Boolean windowsNotificationBool)
	{
		this.WindowsNotificationBool = windowsNotificationBool;
	}

	public List getEmails()
	{
		return Emails;
	}

	public List getEmailInfo()
	{
		return EmailInfo;
	}

	public Boolean getWindowsNotificationBool()
	{
		return WindowsNotificationBool;
	}
}