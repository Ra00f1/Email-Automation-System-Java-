package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Settings
{
	String email;
	String password;
	@FXML
	Button CancelButton;
	@FXML
	TextField EmailTextField;
	@FXML
	TextField PasswordTextField;

	@FXML
	public void initialize() throws IOException
	{
		try
		{
			File myObj = new File("Settings.txt");
			Scanner myReader = new Scanner(myObj);
			String data = myReader.nextLine();
			email = data.split(":")[1];
			data = myReader.nextLine();
			password = data.split(":")[1];
			EmailTextField.setText(email);
			PasswordTextField.setText(password);
		}
		catch (FileNotFoundException e)
		{
			throw new RuntimeException(e);
		}
	}
	public void CancelButton(ActionEvent event) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Email Automation");
		stage.setResizable(false);
		stage.show();
	}
	public void DoneButton(ActionEvent event) throws IOException
	{
		email = EmailTextField.getText();
		password = PasswordTextField.getText();

		ArrayList<String> list = new ArrayList<String>();
		list.add("Email");
		list.add(email);
		list.add("Password");
		list.add(password);
		WriteData(list);

		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Email Automation");
		stage.setResizable(false);
		stage.show();
	}

	public void WriteData(ArrayList<String> list)
	{
		System.out.println(list);
		try {
			File myObj = new File("Settings.txt");
			if (myObj.createNewFile())
			{
				System.out.println("File created: " + myObj.getName());
			}
			else
			{
				System.out.println("File already exists.");
			}
			FileWriter myWriter = new FileWriter("Settings.txt");
			for (int i = 0; i <= list.size()/2; i+=2)
			{
				myWriter.write(list.get(i) + ":" + list.get(i+1));
				myWriter.write("\n");
			}
			myWriter.close();
		}
		catch (IOException e)
		{
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}
