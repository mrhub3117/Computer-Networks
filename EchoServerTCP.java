15.	Write a program to implement a echo client-server communication using TCP sockets.
import java.io.*;
import java.net.*;

public class EchoServerTCP {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Echo Server is running on port 8080...");

            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                    String receivedMessage = in.readLine();
                    System.out.println("Received: " + receivedMessage);
                    out.println(receivedMessage); // Echo message back to client
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
