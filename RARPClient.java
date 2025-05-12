10.	Simulate RARP using UDP.
import java.net.*;
import java.util.Scanner;

public class RARPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("localhost");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter MAC address to resolve: ");
            String macAddress = scanner.nextLine();
            byte[] buffer = macAddress.getBytes();

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, 9876);
            socket.send(packet);

            byte[] responseBuffer = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
            socket.receive(responsePacket);
            String ipAddress = new String(responsePacket.getData(), 0, responsePacket.getLength());

            System.out.println("Resolved IP Address: " + ipAddress);
        }
    }
}


to get output put this as input:
  Enter MAC address to resolve: 00-1B-44-11-3A-B7
Resolved IP Address: 192.168.1.1

Enter MAC address to resolve: 00-1B-44-99-9A-C1
Resolved IP Address: NOT FOUND

