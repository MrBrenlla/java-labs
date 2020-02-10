/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.udc.redes.tutorial.tcp.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Brais
 */
public class ServerThread extends Thread {
    
    private final Socket s;
    
    public ServerThread(Socket s){
        this.s=s;
    }
    
    public void run() {
         
        try{
            // Set the input channel
            BufferedReader in = new BufferedReader(new InputStreamReader(
            s.getInputStream()));
            // Set the output channel
            PrintWriter out= new PrintWriter(s.getOutputStream(),true);
            // Receive the client message
            String mensaxe=in.readLine();
            // Send response to the client
            out.println(mensaxe);
            // Close the streams
            in.close();
            out.close();
        }catch (SocketTimeoutException e) {
            System.err.println("Nothing received in 300 secs");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            try {
                s.close();
            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
