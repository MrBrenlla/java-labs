package es.udc.redes.tutorial.tcp.server;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TcpServer {

    @SuppressWarnings("empty-statement")
    public static void main(String argv[]) {
        if (argv.length != 1) {
            System.err.println("Format: TcpServer <port>");
            System.exit(-1);
        }
        ServerSocket server=null;
        try {
            // Create a server socket
            server = new ServerSocket(Integer.parseInt(argv[0]));
            // Set a timeout of 300 secs
            server.setSoTimeout(300000);
            while (true) {
                // Wait for connections
                Socket s = server.accept();
                ServerThread t = new ServerThread(s);
                t.run();
            }
        // Uncomment next catch clause after implementing the logic            
        } catch (SocketTimeoutException e) {
            System.err.println("Nothing received in 300 secs ");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
              server.close();
            } catch (IOException ex) {
              Logger.getLogger(TcpServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
