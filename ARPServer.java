import java.io.*;
import java.net.*;
import java.util.HashMap;

public class ARPServer {
    public static void main(String[] args) {
        HashMap<String, String> arpTable = new HashMap<>();
        arpTable.put("192.168.1.1", "00-1B-44-11-3A-B7");
        arpTable.put("192.168.1.2", "00-1B-44-22-3A-B8");
        arpTable.put("192.168.1.3", "00-1B-44-33-3A-B9");

        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("ARP Server is running on port 8080...");

            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                    String requestedIP = in.readLine();
                    String macAddress = arpTable.getOrDefault(requestedIP, "MAC Address Not Found");
                    out.println(macAddress);
                    System.out.println("Resolved " + requestedIP + " -> " + macAddress);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
