package es.udc.redes.tutorial.udp.server;

import java.net.*;

/**
 * Implements an UDP Echo Server.
 */
public class UdpServer {

    public static void main(String argv[]) {
        if (argv.length != 1) {
            System.err.println("Format: UdpServer <port_number>");
            System.exit(-1);
        }
        DatagramSocket server=null;
        try {
            // Create a server socket
            server = new DatagramSocket(Integer.parseInt(argv[0]));
            // Set max. timeout to 300 secs
            server.setSoTimeout(300000);
            
            while (true) {
                // Prepare datagram for reception
                byte buf[]=new byte[server.getSendBufferSize()];
                DatagramPacket dp=new DatagramPacket(buf,server.getSendBufferSize());
                // Receive the message
                server.receive(dp);
                // Prepare datagram to send response
                DatagramPacket dp2;
                dp2 = new DatagramPacket(dp.getData(),dp.getLength(),
                        dp.getAddress(),dp.getPort());
                // Send response
                server.send(dp2);
            }
          
        // Uncomment next catch clause after implementing the logic
        //} catch (SocketTimeoutException e) {
        //    System.err.println("No requests received in 300 secs ");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
        if (!(server==null)) server.close();
        }
    }
}
