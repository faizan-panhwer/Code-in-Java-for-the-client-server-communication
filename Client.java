import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            // Create a socket to connect to the server
            socket = new Socket("localhost", 5000);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: localhost");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Unable to get I/O for the connection to: localhost");
            System.exit(1);
        }

        // Prompt the user to enter a number
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter a number: ");
        String userInput = stdIn.readLine();

        // Send the user input to the server
        out.println(userInput);

        // Read the response from the server and print it
        String response;
        while ((response = in.readLine()) != null) {
            System.out.println(response);
        }

        // Close the connections
        out.close();
        in.close();
        stdIn.close();
        socket.close();
    }
}
