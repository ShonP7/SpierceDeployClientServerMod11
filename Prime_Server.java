package com.primeserver;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Prime_Server extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		TextArea ta= new TextArea();
		
		Scene scene = new Scene(ta, 400, 200);
		
		primaryStage.setTitle("Server");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		new Thread( () -> {
			
			
			try {
				ServerSocket serverSocket = new ServerSocket(8001);
				Platform.runLater( () -> 
					ta.appendText("Server started at: " + new Date() + '\n'));
				
				Socket socket = serverSocket.accept();
				
				DataInputStream fromClient = new DataInputStream(socket.getInputStream());
				
				double number = fromClient.readDouble();
				
				double temp = isPrimeNumber(); 
				
				PrintStream p = new PrintStream(socket.getOutputStream());
				p.println(temp);
				
				DataOutputStream fromServer = new DataOutputStream(socket.getOutputStream());
				fromServer.writeDouble(temp);
				
				
			} catch (IOException e1) {
				
				e1.printStackTrace(); 
			
			
			}				
			}).start();
			
	}	

	
	public static void main(String[] args) throws IOException {
					
					launch(args);
				}
			
	
	
	public static double isPrimeNumber() {
		
				double num = 0;
				boolean isPrime = true;
			
			for(int i = 2; i < num; i++) {
				
				if(num%i == 0) {
					
					isPrime = false;
					break;
				}
			}
			
			if(isPrime) {
				
				System.out.println("\n" + num + " is a prime number");
				
			} else {
				
				System.out.println("\n" + num + " is not a prime number");
				
			}
			return num;
		}

	}


//fromServer.println(isPrimeNumber());
		
//		BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//		PrintWriter fromServer = new PrintWriter(socket.getOutputStream());
//		fromServer.flush();
		
		
//		while(true) {
//			
//			double number = fromClient.readDouble();
//			
//			Prime_Server.isPrimeNumber();
//			
//			Platform.runLater(() ->{
//				ta.appendText("Number received from client: " +  '\n');
//				
//				ta.appendText("Result is: " + number );
//			});
//		}
//		
//	} catch (IOException e) {
//		e.printStackTrace();
//	}
//}).start();	
//}
 	