// MathServer.java: A simple TCP math server.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MathServer {
    public static void main(String[] args) {
        int port = 10000;

        if (args.length == 1) {
            port = Integer.parseInt(args[0]);
        } else {
            System.out.println("Use the default setting...");
        }

        MathService mathService = new PlainMathService();

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(socket.getInputStream()));
                        BufferedWriter writer = new BufferedWriter(
                                new OutputStreamWriter(socket.getOutputStream()))) {
                    String request = reader.readLine();
                    if (request == null) {
                        continue;
                    }

                    writer.write(String.valueOf(handleRequest(request, mathService)));
                    writer.newLine();
                    writer.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static double handleRequest(String request, MathService mathService) {
        String[] parts = request.split(":");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid request format: " + request);
        }

        String operator = parts[0];
        double firstValue = Double.parseDouble(parts[1]);
        double secondValue = Double.parseDouble(parts[2]);

        switch (operator) {
            case "+":
                return mathService.add(firstValue, secondValue);
            case "-":
                return mathService.sub(firstValue, secondValue);
            case "*":
                return mathService.mul(firstValue, secondValue);
            case "/":
                return mathService.div(firstValue, secondValue);
            default:
                throw new IllegalArgumentException("Unsupported operator: " + operator);
        }
    }
}
