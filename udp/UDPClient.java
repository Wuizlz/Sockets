// UDPClient.java: A simple UDP client program.
import java.net.*;
import java.io.*;

public class UDPClient {
    public static void main(String[] args) {
        DatagramSocket aSocket = null;
        if (args.length < 3) {
            System.out.println("Usage: java UDPClient <message> <Host name> <Port number>");
            System.exit(1);
        }

        try {
            aSocket = new DatagramSocket();
            byte[] m = args[0].getBytes();
            InetAddress aHost = InetAddress.getByName(args[1]);
            int serverPort = Integer.parseInt(args[2]);

            DatagramPacket request = new DatagramPacket(m, m.length, aHost, serverPort);
            aSocket.send(request);

            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);
            System.out.println("Reply: " + new String(reply.getData(), 0, reply.getLength()));
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null) {
                aSocket.close();
            }
        }
    }
}
