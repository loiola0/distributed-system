package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        
        int port = 1234;
        try (Socket socket = new Socket("localhost", port)) {
            
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
  
            
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
  
            
            Scanner sc = new Scanner(System.in);
            String line = null;
            int cont = 0;
            Random gerador = new Random();
            int valorTotal = 0;
            int numberRandomNumbers = 1000000;
            int intervalRandomNumber = 1001;
            
            
            while (cont < numberRandomNumbers) {  
               
                int valor = gerador.nextInt(intervalRandomNumber);
                line = Integer.toString(valor);
  
                
                out.println(line);
                out.flush();
  
                
                cont += 1;   
                
                valorTotal = Integer.parseInt(in.readLine());
            }
           
            
            sc.close();
            System.out.println("Valor final: "+ valorTotal);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
