package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.event.ActionEvent;

public class SceneController
{
	private Stage stage;
	private Scene scene;
	private Parent root;

	public void SwitchToResipentsList(ActionEvent event) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("ResipentsList.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void SwitchToMain(ActionEvent event) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void SwitchToSettings2(ActionEvent event) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("ResipentsList.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
