10.	Simulate RARP using UDP.
import java.net.*;
import java.util.HashMap;

public class RARPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(9876);
        byte[] buffer = new byte[1024];

        
        HashMap<String, String> rarpTable = new HashMap<>();
        rarpTable.put("00-1B-44-11-3A-B7", "192.168.1.1");
        rarpTable.put("00-1B-44-22-3A-B8", "192.168.1.2");
        rarpTable.put("00-1B-44-33-3A-B9", "192.168.1.3");

        System.out.println("RARP Server running on UDP port 9876...");

        while (true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String macAddress = new String(packet.getData(), 0, packet.getLength()).trim();

            String ipAddress = rarpTable.getOrDefault(macAddress, "NOT FOUND");
            byte[] response = ipAddress.getBytes();
            DatagramPacket responsePacket = new DatagramPacket(response, response.length, packet.getAddress(), packet.getPort());
            socket.send(responsePacket);
            System.out.println("Resolved MAC: " + macAddress + " -> IP: " + ipAddress);
        }
    }
}
