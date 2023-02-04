package com.example.demo;

import com.example.demo.Helper_Class.HelperClass;
import com.example.demo.Helper_Class.WriterClass;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ResipentsListController extends HelperClass implements WriterClass
{
	@FXML
	ListView ResipentsList;

	Map<String, Map> Data = new LinkedHashMap<String, Map>();
	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	public void initialize() throws IOException
	{
		ShowListView();
	}
	public void SwitchToMain(ActionEvent event) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Email Automation");
		stage.setResizable(false);
		stage.show();
	}
	public void RemoveResipent() throws IOException
	{
		String Selected = (String) ResipentsList.getSelectionModel().selectedItemProperty().getValue();
		String Key = Selected.split(" ")[2];
		System.out.println(Key);
		Data.remove(Key);
		WriteData(Data);
		ShowListView();
	}

	public void ClearAll() throws IOException
	{
		Data.clear();
		WriteData(Data);
		ShowListView();
	}

	public void AddNewResipent(ActionEvent event) throws IOException
	{
		ShowListView();
		System.out.println(ResipentsList);
		try
		{
			Parent root = FXMLLoader.load(getClass().getResource("AddResipent.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Add New Recipient");
			stage.setScene(new Scene(root, 400, 150));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void ShowListView() throws IOException
	{
		ObservableList<String> data = FXCollections.observableArrayList();
		Data = ReadData();
		for (Map maps : Data.values())
		{
			List ListofKeys = new ArrayList(maps.keySet());
			String tempstring = ListofKeys.get(0) + " " + ListofKeys.get(1) + " " + ListofKeys.get(2);
			data.add(tempstring);
		}
		System.out.println(data);
		ResipentsList.setItems(data);
	}
	public void RefreshButton() throws IOException
	{
		ShowListView();
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
