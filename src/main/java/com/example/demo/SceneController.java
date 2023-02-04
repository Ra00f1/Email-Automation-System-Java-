package com.example.demo;

import com.example.demo.Helper_Class.Window_Notification_System;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

import javafx.event.ActionEvent;

public class SceneController
{
	private Stage stage;
	private Scene scene;
	private Parent root;
	public void SwitchToResipentsList(ActionEvent event) throws IOException, AWTException
	{
		Parent root = FXMLLoader.load(getClass().getResource("ResipentsList.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Email Automation");
		stage.setResizable(false);
		stage.show();
	}
	public void SwitchToSendEmail(ActionEvent event) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("SendEmailWindow.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Email Automation");
		stage.setResizable(false);
		stage.show();
	}
}
