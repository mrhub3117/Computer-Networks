import java.io.*;
import java.net.*;

public class Client{
    public static void main (String[] args) {
        try(Socket socket = new Socket("localhost",8080);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(),true)){
            String message = "how are you";
            out.println(message);
            System.out.println("Send:"+message);
            
            String response = in.readLine();
            System.out.println("Received:"+response);
            
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
            
        }
    }
