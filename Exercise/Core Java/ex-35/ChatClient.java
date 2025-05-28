// ChatClient.java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    private static final String SERVER_IP = "127.0.0.1"; // Loopback address for local testing
    private static final int PORT = 12345;

    public static void main(String[] args) {
        System.out.println("Connecting to server at " + SERVER_IP + ":" + PORT + "...");
        try (Socket socket = new Socket(SERVER_IP, PORT)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // Auto-flush
            Scanner clientInput = new Scanner(System.in);

            String serverMessage;
            String clientMessage;

            // Client continuously sends messages and reads server responses
            while (true) {
                // Send to server
                System.out.print("Client: ");
                clientMessage = clientInput.nextLine();
                out.println(clientMessage);
                if (clientMessage.equalsIgnoreCase("bye")) {
                    System.out.println("Client said bye. Closing connection.");
                    break; // Exit loop if client sends "bye"
                }

                // Read from server
                if ((serverMessage = in.readLine()) != null) {
                    System.out.println("Server: " + serverMessage);
                    if (serverMessage.equalsIgnoreCase("bye")) {
                        System.out.println("Server said bye. Closing connection.");
                        break; // Exit loop if server sends "bye"
                    }
                }
            }
            clientInput.close();
        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Client disconnected.");
    }
}