package com.example.demo;

import java.awt.*;
import javax.swing.*;

public class Splash_Screen extends JWindow {
	Image splashScreen;
	ImageIcon imageIcon;
	public Splash_Screen() {
		splashScreen = Toolkit.getDefaultToolkit().getImage("C:\\Users\\LENOVO\\Desktop\\JavaProject\\images\\2.png");
		// Create ImageIcon from Image
		imageIcon = new ImageIcon(splashScreen);
		// Set JWindow size from image size
		setSize(imageIcon.getIconWidth(),imageIcon.getIconHeight());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// Get x coordinate on screen for make JWindow locate at center
		int x = (screenSize.width-getSize().width)/2;
		// Get y coordinate on screen for make JWindow locate at center
		int y = (screenSize.height-getSize().height)/2;
		setLocation(x,y);
		setVisible(true);
		try {
			// Make JWindow appear for 2 seconds before disappear
			Thread.sleep(2000);
			this.dispose();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	// Paint image onto JWindow
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(splashScreen, 0, 0, this);
	}
}