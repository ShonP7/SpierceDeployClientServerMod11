package com.prime_client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class PrimeClient extends Application {
	
	DataInputStream fromPrimeServer;
	DataOutputStream toPrimeServer;

	double temp;
	
	@Override
public void start(Stage primaryStage) throws Exception {
	
		Button btn = new Button("submit");

		BorderPane textFieldPane = new BorderPane();
		textFieldPane.setPadding(new Insets(5,5,10,10));
		textFieldPane.setStyle("-fx-border-color: grey");
		textFieldPane.setLeft(new Label("Enter any number: "));
		
		
		TextField userInputField = new TextField();
		userInputField.setAlignment(Pos.TOP_LEFT);
		textFieldPane.setCenter(userInputField);	
		
		
		BorderPane root = new BorderPane();
		TextArea rootTextArea = new TextArea();
		root.setTop(textFieldPane);
		root.setCenter(btn);
		btn.setStyle("-fx-border-color: blue");
		
		
		Scene scene = new Scene(root, 400, 200);
		primaryStage.setTitle("Client");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		Socket socket = new Socket("localhost", 8001);
		
btn.setOnAction(e ->{
			
			
			double number = Double.parseDouble(userInputField.getText());
			
			try {
//				PrintStream p = new PrintStream(socket.getOutputStream());
//				p.println(number);
//				p.flush();
//				
				
				toPrimeServer.writeDouble(number);
				toPrimeServer.flush();
				
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}	
		});

		
		//Scanner sc1 = new Scanner(socket.getInputStream());
		//fromPrimeServer = new DataInputStream(socket.getInputStream());
		//fromPrimeServer.readDouble();
		//double temp = sc1.nextDouble();
		//System.out.println(temp);
		

	
	}	

public static void main(String[] args) throws IOException {
				
		launch(args);
	}
}






//Scanner user = new Scanner(System.in);
//double number = user.nextInt(); 
//try {
//	double number = Double.parseDouble(userInputField.getText().trim());
//	
//	PrimeClient.isPrimeNumber();
//	
//	toPrimeServer.writeDouble(number);
//	toPrimeServer.flush();
//		
//	rootTextArea.appendText("Users input is: " + number + '\n');
//		
//		
//		
//	} catch (IOException e1) {
//		
//		e1.printStackTrace();
//	}		




