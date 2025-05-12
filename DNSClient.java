4.	Simulate DNS resolution using UDP sockets.
import java.net.*;
import java.util.Scanner;

public class DNSClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("localhost");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter domain name: ");
            String domain = scanner.nextLine();
            byte[] buffer = domain.getBytes();

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
