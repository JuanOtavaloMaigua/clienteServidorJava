/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package serverclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
//import serverclient.Reunion;

/**
 *
 * @author JuanOtavalo
 */
public class Server extends Application{

    @Override
    public void start(Stage primaryStage) {
        TextArea ta = new TextArea();
        Scene scene = new Scene(new ScrollPane(ta),450, 200);
        primaryStage.setTitle("Server");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(8000);
                Platform.runLater(()-> ta.appendText("Server started at "+ new Date() + '\n'));
                
                Socket socket = serverSocket.accept();
                ObjectInputStream inputFromClient = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream outputToClient = new ObjectOutputStream(socket.getOutputStream());

                while(true){
                    /*double radius = inputFromClient.readDouble();
                    double area = radius * radius * Math.PI;
                    outputToClient.writeDouble(area);*/
                    Reunion radius = (Reunion) inputFromClient.readObject();
                    Platform.runLater(() -> {ta.appendText("Radius received from clien: "+radius + '\n');
                    ta.appendText("Area is: +");});
                    
                }
                
            } catch (IOException ex) {
                ex.printStackTrace();            
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
).start();
    }
    
    /*public static void main(String[] args){
        launch(args);
    }*/
}
