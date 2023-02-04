package com.example.demo.Helper_Class;

import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class HelperClass
{
	public Map ReadData() throws IOException
	{
		Map<String, Map> OldData = new LinkedHashMap<>();
		try {
			File myObj = new File("Data.txt");
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
		catch (FileNotFoundException e)
		{
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return OldData;
	}
	///////////////////////////////////////////////////////////////Overload
	public List<String> ReadData(String filename) throws IOException
	{
		List<String> SelectedEmails = new ArrayList<>();
		try {
			File myObj = new File("TempEmails.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine())
			{
				String data = myReader.nextLine();
				System.out.println(data);
				SelectedEmails.add(data);
			}
			myReader.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return SelectedEmails;
	}

	public void AlertFunction(Alert.AlertType AlertType, String WarningText)
	{
		Alert alert = new Alert(AlertType);
		alert.setTitle("Warning!");
		alert.setHeaderText(null);
		alert.setContentText(WarningText);
		alert.showAndWait();
	}
}
