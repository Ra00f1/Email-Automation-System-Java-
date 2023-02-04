package com.example.demo.Helper_Class;

import java.awt.*;
import java.awt.TrayIcon.MessageType;

public class Window_Notification_System
{
	public Window_Notification_System() throws AWTException
	{
		if (SystemTray.isSupported()) {
			displayTray();
		} else {
			System.err.println("System tray not supported!");
		}
	}
	public void displayTray() throws AWTException {
		//Obtain only one instance of the SystemTray object
		SystemTray tray = SystemTray.getSystemTray();

		//If the icon is a file
		Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
		//Alternative (if the icon is on the classpath):
		//Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

		TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
		//Let the system resize the image if needed
		trayIcon.setImageAutoSize(true);
		//Set tooltip text for the tray icon
		trayIcon.setToolTip("System tray icon demo");
		tray.add(trayIcon);

		trayIcon.displayMessage("Notification System", "Your Emails were sent!", MessageType.INFO);
	}
}