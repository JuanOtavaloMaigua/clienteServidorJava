/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

/**
 *
 * @author JuanOtavalo
 */
public class HandleClient implements Runnable{
    private Socket socket;
    private TextArea ta;

    public HandleClient(Socket socket, TextArea ta) {
        this.socket = socket;
        this.ta = ta;
    }

    @Override
    public void run() {
        try {
            DataInputStream inputFroClient = new DataInputStream(socket.getInputStream());
            DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
           
            while(true){
                double radio = inputFroClient.readDouble();
                double area = radio * radio * Math.PI;
                outputToClient.writeDouble(3.3);
                Platform.runLater(()->{
                    ta.appendText("Radio received: "+ radio+"\n");
                    ta.appendText("Area calculated: "+ area + "\n");
                });
            } 
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
    
    
}
