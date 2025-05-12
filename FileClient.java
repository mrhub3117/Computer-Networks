3.	Implement a file transfer protocol using TCP where the client requests a file and the server sends it if available.
import java.io.*;
import java.net.*;

public class FileClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8080);
             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream())) {

            System.out.print("Enter file name to request: ");
            String requestedFile = consoleReader.readLine();
            out.writeUTF(requestedFile);

            String serverResponse = in.readUTF();
            if ("FOUND".equals(serverResponse)) {
                FileOutputStream fos = new FileOutputStream("C:/" + requestedFile); // Save to C:/ drive
                byte[] buffer = new byte[4096];
                int bytesRead;

                while ((bytesRead = in.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
                fos.close();
                System.out.println("File received and saved in C:/ drive: " + requestedFile);
            } else {
                System.out.println("File not found on server.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
