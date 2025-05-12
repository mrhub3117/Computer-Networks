1)http client-server
import java.io.*;
import java.net.*;

public class Server
{
	public static void main(String[] args) {
       try(ServerSocket serverSocket = new ServerSocket(8080)){
       System.out.println("Server is running on port 8080");
       
       while(true){
           try(Socket socket=serverSocket.accept();
       BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
       PrintWriter out= new PrintWriter(socket.getOutputStream(),true)){
           String received = in.readLine();
           System.out.println("Received:"+received);
           
           if(received!=null){
               String reverse = new StringBuilder(received).reverse().toString();
               out.println(reverse);
               System.out.println("send:"+reverse);
               
           }
       }
       }
       
	}
	catch(IOException e){
	    e.printStackTrace();
	}
}
}
