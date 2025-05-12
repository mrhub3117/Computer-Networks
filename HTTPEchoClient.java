9.	Write a TCP socket program for a simple HTTP echo client that downloads a web page from a given host.
import java.io.*;
import java.net.*;

public class HTTPEchoClient {
    public static void main(String[] args) {
        String host = "example.com"; // Change this to any website
        int port = 80; // HTTP default port

        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Send HTTP GET request
            out.println("GET / HTTP/1.1");
            out.println("Host: " + host);
            out.println("Connection: close");
            out.println(); // Blank line to indicate end of headers

            // Receive and print response
            String responseLine;
            while ((responseLine = in.readLine()) != null) {
                System.out.println(responseLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

