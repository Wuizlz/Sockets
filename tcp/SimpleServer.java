// SimpleServer.java: A simple server program.
import java.net.*;
import java.io.*;

public class SimpleServer {
    public static void main(String[] args) throws IOException {
        // Register service on port 1254.
        ServerSocket s = new ServerSocket(1254);

        // Wait for a client connection and send one message.
        Socket s1 = s.accept();
        OutputStream s1out = s1.getOutputStream();
        DataOutputStream dos = new DataOutputStream(s1out);

        dos.writeUTF("Hi there");

        // Close the connection and exit.
        dos.close();
        s1out.close();
        s1.close();
        s.close();
    }
}
