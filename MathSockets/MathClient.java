// MathClient.java: A test client program to access MathServer.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MathClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 10000;

        if (args.length != 2) {
            System.out.println("Use the default setting...");
        } else {
            hostname = args[0];
            port = Integer.parseInt(args[1]);
        }

        try (
                Socket socket = new Socket(hostname, port);
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()))) {
            // Perform a simple math operation: 12 + 21.
            writer.write("+:12:21");
            writer.newLine();
            writer.flush();

            System.out.println(reader.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
