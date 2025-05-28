// ChatServer.java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        System.out.println("Server started. Listening on port " + PORT + "...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            Socket clientSocket = serverSocket.accept(); // Blocks until a client connects
            System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); // Auto-flush
            Scanner serverInput = new Scanner(System.in);

            String clientMessage;
            String serverResponse;

            // Server continuously reads client messages and sends responses
            while (true) {
                // Read from client
                if ((clientMessage = in.readLine()) != null) {
                    System.out.println("Client: " + clientMessage);
                    if (clientMessage.equalsIgnoreCase("bye")) {
                        System.out.println("Client said bye. Closing connection.");
                        break; // Exit loop if client sends "bye"
                    }
                }

                // Send to client
                System.out.print("Server: ");
                serverResponse = serverInput.nextLine();
                out.println(serverResponse);
                if (serverResponse.equalsIgnoreCase("bye")) {
                    System.out.println("Server said bye. Closing connection.");
                    break; // Exit loop if server sends "bye"
                }
            }
            serverInput.close();
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Server stopped.");
    }
}