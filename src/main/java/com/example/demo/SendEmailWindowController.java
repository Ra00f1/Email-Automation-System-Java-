package com.example.demo;

import com.example.demo.Helper_Class.HelperClass;
import com.example.demo.Helper_Class.WriterClass;
import eu.hansolo.tilesfx.tools.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import javafx.scene.Parent;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailWindowController extends HelperClass
{
	List<String> Emails;
	String emails;
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	Button CancelButton;
	@FXML
	ComboBox HourBox;
	@FXML
	ComboBox MinuteBox;
	@FXML
	DatePicker datePicker;
	@FXML
	TextField EmailHeader;
	@FXML
	TextArea EmailBody;
	@FXML
	CheckBox WIndowsNotificationToggle;

	@FXML
	public void initialize() throws IOException
	{
		if (Emails == null)
		{
			WriteData(Emails = Collections.emptyList());
		}
		else
		{
			WriteData(Emails);
		}
		for (int i = 00; i <= 60; i++)
		{
			MinuteBox.getItems().add(i);
		}
		for (int i = 00; i < 24; i++)
		{
			HourBox.getItems().add(i);
		}
	}

	public void SetUpResipents(List emails) throws IOException
	{
		this.Emails = emails;
		WriteData(Emails);
	}

	public void WriteData(List<String> emails)
	{
		try {
			File myObj = new File("TempEmails.txt");
			if (myObj.createNewFile())
			{
				System.out.println("File created: " + myObj.getName());
			}
			else
			{
				System.out.println("File already exists.");
			}
			FileWriter myWriter = new FileWriter("TempEmails.txt");
			for (String email : emails)
			{
				myWriter.write(email);
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

	public void Send(ActionEvent event) throws IOException
	{
		String EmailSubject = EmailHeader.getText();
		String EmailText = EmailBody.getText();
		if(EmailSubject == "")
		{
			AlertFunction(Alert.AlertType.WARNING, "Email doesn't have a subject!");
		}
		else if (EmailText == "")
		{
			AlertFunction(Alert.AlertType.WARNING, "Email doens't have a body or text!");
		}
		else if (datePicker.getValue() == null || HourBox.getValue() == null || MinuteBox == null)
		{
			AlertFunction(Alert.AlertType.WARNING, "One of the Fields for date or time was left empty!");
		}
		else if (ReadData("TempEmails") == null || ReadData("TempEmails").toString() == "[]")
		{
			AlertFunction(Alert.AlertType.WARNING, "No recipient selected!");
		}
		else
		{
			List date = List.of(datePicker.getValue().toString().split("-"));

			int Year = Integer.parseInt((String) date.get(0));
			int Month = Integer.parseInt((String) date.get(2));
			int Day = Integer.parseInt((String) date.get(1));
			int Hour = (int) HourBox.getValue();
			int Minute = (int) MinuteBox.getValue();
			ArrayList SnedInfo = new ArrayList();
			SnedInfo.add(Year);
			SnedInfo.add(Month);
			SnedInfo.add(Day);
			SnedInfo.add(Hour);
			SnedInfo.add(Minute);
			SnedInfo.add(EmailSubject);
			SnedInfo.add(EmailText);

			Timer_and_EmailSender_Class EmailSenderClass = new Timer_and_EmailSender_Class(ReadData("TempEmails"), SnedInfo, WIndowsNotificationToggle.isSelected());

			AlertFunction(Alert.AlertType.INFORMATION, "Your Emails are set and will be sent when the selected time arrives and a notification will be pushed to your PC if you have chosen so, Please do not turn your PC off!");

			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		}
	}

	public void OpenChooseResipentListWindow(ActionEvent event) throws IOException
	{
		try
		{
			Parent root = FXMLLoader.load(getClass().getResource("ChooseResipents.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Choose Recipients");
			stage.setScene(new Scene(root, 600, 400));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false);
			stage.show();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public void CancelButton(ActionEvent event) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
}