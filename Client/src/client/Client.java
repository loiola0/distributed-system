package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        // establish a connection by providing host and port
        // number
        try (Socket socket = new Socket("localhost", 1234)) {
            // writing to server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
  
            // reading from server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
  
            // object of scanner class
            Scanner sc = new Scanner(System.in);
            String line = null;
            int cont = 0;
            Random gerador = new Random();
            
            while (cont < 1000000) {  
                // reading from user
                int valor = gerador.nextInt(1001);
                line = Integer.toString(valor);
  
                // sending the user input to server
                out.println(line);
                out.flush();
  
                // displaying server reply
                in.readLine();
                cont += 1;
            }
            
            // closing the scanner object
            sc.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
