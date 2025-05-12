Implement a file transfer protocol using TCP where the client requests a file and the server sends it if available.

import java.io.*;
import java.net.*;

public class FileServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server is running on port 8080...");

            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

                    String requestedFile = in.readLine();
                    File file = new File("C:/" + requestedFile); // Updated to search in C:/ drive

                    if (file.exists() && file.isFile()) {
                        out.writeUTF("FOUND");
                        FileInputStream fis = new FileInputStream(file);
                        byte[] buffer = new byte[4096];
                        int bytesRead;

                        while ((bytesRead = fis.read(buffer)) != -1) {
                            out.write(buffer, 0, bytesRead);
                        }
                        fis.close();
                        System.out.println("File sent: " + requestedFile);
                    } else {
                        out.writeUTF("NOT FOUND");
                        System.out.println("File not found: " + requestedFile);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
