import java.io.*;
import java.net.*;

public class ARPClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8080);
             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.print("Enter IP address to resolve: ");
            String requestedIP = consoleReader.readLine();
            out.println(requestedIP);

            String response = in.readLine();
            System.out.println("MAC Address: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



use these ip address to get output:
Enter IP address to resolve: 192.168.1.1
MAC Address: 00-1B-44-11-3A-B7

Enter IP address to resolve: 192.168.1.100
MAC Address: MAC Address Not Found
