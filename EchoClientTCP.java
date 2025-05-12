15.	Write a program to implement a echo client-server communication using TCP sockets.
import java.io.*;
import java.net.*;

public class EchoClientTCP {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8080);
             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.print("Enter message: ");
            String message = consoleReader.readLine();
            out.println(message);

            String response = in.readLine();
            System.out.println("Echoed from server: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
