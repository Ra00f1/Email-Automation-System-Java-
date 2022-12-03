package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class main extends Application
{
	public static void main(String[] args)
	{
		//Splash_Screen splash = new Splash_Screen();
		launch(args);
	}
	@Override
	public void start(Stage stage) throws Exception
	{
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
