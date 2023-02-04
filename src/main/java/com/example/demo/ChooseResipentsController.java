package com.example.demo;

import com.example.demo.Helper_Class.HelperClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class ChooseResipentsController extends HelperClass
{
	int FileReadCounter = 0;
	Map<String, Map> Data = new LinkedHashMap<>();
	Map<String, Map> ChosenEmails = new LinkedHashMap<>();
	@FXML
	Button CancelButton;
	@FXML
	ListView AllContactsList;
	@FXML
	ListView SelectedList;

	@FXML
	public void initialize() throws IOException
	{
		Data = ReadData();
		FileReadCounter +=1;
		ChosenEmails = ReadData();
		ShowListView(AllContactsList, Data);
	}
//////////////////////////////////////////////////////////Override
	@Override
	public Map ReadData() throws IOException
	{
		Map<String, Map> OldData = new LinkedHashMap<>();
		try {
			File myObj;
			if(FileReadCounter == 0)
			{
				myObj = new File("Data.txt");
				Scanner myReader = new Scanner(myObj);
				while (myReader.hasNextLine())
				{
					Map<String, String> tempMap = new HashMap<String, String>();
					String data = myReader.nextLine();
					java.util.List mapdata = List.of(data.split(" "));
					tempMap.put((String) mapdata.get(0), (String) mapdata.get(0));
					tempMap.put((String) mapdata.get(1), (String) mapdata.get(1));
					tempMap.put((String) mapdata.get(2), (String) mapdata.get(2));
					OldData.put((String) mapdata.get(2), tempMap);
				}
				myReader.close();
			}
			else
			{
				myObj = new File("TempEmails.txt");
				Scanner myReader = new Scanner(myObj);
				while (myReader.hasNextLine())
				{
					Map<String, String> tempMap = new HashMap<String, String>();
					String data = myReader.nextLine();
					tempMap.put(data, data);
					OldData.put(data, tempMap);
				}
				myReader.close();
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return OldData;
	}
	public void RefreshButton() throws IOException
	{
		FileReadCounter = 0;
		Data = ReadData();
		FileReadCounter +=1;
		ChosenEmails = ReadData();
		ShowListView(AllContactsList, Data);
	}
	public void ShowListView(ListView List, Map<String, Map> map) throws IOException
	{
		ObservableList<String> data = FXCollections.observableArrayList();
		System.out.println(map);
		for (Map maps : map.values())
		{
			List ListofKeys = new ArrayList(maps.keySet());
			String tempstring = ListofKeys.get(0) + " " + ListofKeys.get(1) + " " + ListofKeys.get(2);
			data.add(tempstring);
		}
		System.out.println(data);
		List.setItems(data);
	}

	public void AddTOList() throws IOException
	{
		int index = AllContactsList.getSelectionModel().getSelectedIndex();
		SelectedList.getItems().add(AllContactsList.getItems().get(index));
		AllContactsList.getItems().remove(index);
	}

	public void RemoveFromList() throws IOException
	{
		int index = SelectedList.getSelectionModel().getSelectedIndex();
		AllContactsList.getItems().add(SelectedList.getItems().get(index));
		SelectedList.getItems().remove(index);
	}

	public void DoneChoosingButton() throws IOException
	{
		if(SelectedList.getItems().toString() == "[]")
		{
			AlertFunction(Alert.AlertType.WARNING, "The program needs at lest one email to work perfectly.");
		}
		else {
			String items2 = (String) SelectedList.getItems().stream().map(Object::toString).collect(Collectors.joining(", "));
			List items = List.of(items2.split(", "));
			List emails = new ArrayList();
			for (Object item : items)
			{
				//the line below should work but doesn't in the laboratory PCs
				//emails.add(item.toString().split(" ")[2]);
				for (int i = 0; i < 3; i++)
				{
					if(item.toString().split(" ")[i].contains("@"))
						emails.add(item.toString().split(" ")[i]);
				}
			}

			FXMLLoader loader = new FXMLLoader(getClass().getResource("SendEmailWindow.fxml"));
			Parent root = loader.load();
			SendEmailWindowController resipentsListController = loader.getController();
			resipentsListController.SetUpResipents(emails);

			Stage stage = (Stage) CancelButton.getScene().getWindow();
			stage.close();
		}
	}

	public void AddNewResipent(ActionEvent event) throws IOException
	{
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
	public void CancelButton(ActionEvent event) throws IOException
	{
		Stage stage = (Stage) CancelButton.getScene().getWindow();
		stage.close();
	}
}

