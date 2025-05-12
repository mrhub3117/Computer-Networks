4.	Simulate DNS resolution using UDP sockets.
import java.net.*;
import java.util.HashMap;

public class DNSServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(9876);
        byte[] buffer = new byte[1024];

        // Simulated DNS records
        HashMap<String, String> dnsRecords = new HashMap<>();
        dnsRecords.put("google.com", "142.250.190.14");
        dnsRecords.put("microsoft.com", "20.112.52.29");
        dnsRecords.put("example.com", "93.184.216.34");

        System.out.println("DNS Server running on UDP port 9876...");

        while (true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String domain = new String(packet.getData(), 0, packet.getLength()).trim();

            String ipAddress = dnsRecords.getOrDefault(domain, "NOT FOUND");
            byte[] response = ipAddress.getBytes();
            DatagramPacket responsePacket = new DatagramPacket(response, response.length, packet.getAddress(), packet.getPort());
            socket.send(responsePacket);
            System.out.println("Resolved " + domain + " -> " + ipAddress);
        }
    }
}
