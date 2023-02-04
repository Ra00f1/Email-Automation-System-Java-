package com.example.demo;

import com.example.demo.Helper_Class.HelperClass;
import com.example.demo.Helper_Class.WriterClass;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class AddResipentController extends HelperClass implements WriterClass
{
	Map<String, Map> Data = new LinkedHashMap<String, Map>();
	@FXML
	Button CancelButton;
	@FXML
	TextField NameTF;
	@FXML
	TextField SureNameTF;
	@FXML
	TextField EmailTF;

	public void CancelButton(ActionEvent event) throws IOException
	{
		Stage stage = (Stage) CancelButton.getScene().getWindow();
		stage.close();
	}
	public void AddButton(ActionEvent event) throws IOException
	{
		Map<String, String> tempData = new LinkedHashMap<String, String>();
		Data = ReadData();

		String name = NameTF.getText();
		String surename = SureNameTF.getText();
		String email = EmailTF.getText();
		if(name == "" || surename == "" || email == "")
		{
			AlertFunction(Alert.AlertType.WARNING, "One of the Fields is empty");
		}
		else if (name.contains(" ") || surename.contains(" ") || email.contains(" "))
		{
			AlertFunction(Alert.AlertType.WARNING, "One of the Fields contain one or more spaces");
		}
		else
		{
			tempData.put(name, name);
			tempData.put(surename, surename);
			tempData.put(email, email);

			Data.put(email, tempData);
			WriteData(Data);

			Stage stage = (Stage) CancelButton.getScene().getWindow();
			stage.close();
			ShowListView();
		}
	}

	public void ShowListView() throws IOException
	{
		Data = ReadData();
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ResipentsList.fxml"));
			Parent root = loader.load();
			ResipentsListController resipentsListController = loader.getController();
			resipentsListController.ShowListView();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	@Override
	public void WriteData(Map data)
	{
		try {
			File myObj = new File("Data.txt");
			if (myObj.createNewFile())
			{
				System.out.println("File created: " + myObj.getName());
			}
			else
			{
				System.out.println("File already exists.");
			}
			FileWriter myWriter = new FileWriter("Data.txt");
			for (Map maps : Data.values())
			{
				List ListofKeys = new ArrayList(maps.keySet());
				myWriter.write(ListofKeys.get(0) + " " + ListofKeys.get(1) + " " + ListofKeys.get(2));
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
