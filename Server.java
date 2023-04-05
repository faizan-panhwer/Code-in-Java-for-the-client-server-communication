import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        try {
            // Create a server socket to listen for client connections
            serverSocket = new ServerSocket(5000);
            System.out.println("Server started.");
        } catch (IOException e) {
            System.err.println("Could not listen on port: 5000");
            System.exit(1);
        }

        Socket clientSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            // Wait for a client to connect
            clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket);

            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Read the user input from the client
            String userInput = in.readLine();
            int num = Integer.parseInt(userInput);

            // Check if the number is even or odd
            String result = (num % 2 == 0) ? "even" : "odd";

            // Generate the multiplication table for the number
            StringBuilder table = new StringBuilder();
            for (int i = 1; i <= 10; i++) {
                table.append(num).append(" x ").append(i).append(" = ").append(num * i).append("\n");
            }

            // Send the result and table back to the client
            out.println("The number is " + result + ".");
            out.println(table.toString());

        } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        } finally {
            // Close the connections
            out.close();
            in.close();
            clientSocket.close();
            serverSocket.close();
            System.out.println("Server stopped.");
        }
    }
}
