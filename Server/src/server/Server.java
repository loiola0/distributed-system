package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;

public class Server {

    public static void main(String[] args) {
        ServerSocket server = null;

        try {
            int port = 1234;
            server = new ServerSocket(port);
            server.setReuseAddress(true);
            
            System.out.printf("Servidor rodando na porta: "+port);
            
            while (true) {
               
                Socket client = server.accept();

                
                System.out.println("New client connected" + client.getInetAddress().getHostAddress());

                Random randomNumberClient = new Random();

                randomNumberClient.nextInt(3);

                int numberClient = randomNumberClient + 1;
                
                ClientHandler clientSock = new ClientHandler(client,numberClient);

                
                new Thread(clientSock).start().;
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    
    private static class ClientHandler implements Runnable {

        private final Socket clientSocket;
        private static int accumulatorSum = 0;

        private static int numberClient = 0;
        
        public ClientHandler(Socket socket,int numberClient) {
            this.clientSocket = socket;
            this.numberClient = numberClient;
        }

        public void run() {
            PrintWriter out = null;
            BufferedReader in = null;

            try {
                
                out = new PrintWriter(clientSocket.getOutputStream(), true);

                
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String line = in.readLine();
                while ( line != null) {
                    
                    accumulatorSum += Integer.parseInt(line);
                    System.out.printf(" Recebido do cliente %d valor = %d\n", numberClient,Integer.parseInt(line));
                    out.println(Integer.toString(accumulatorSum));
                    line = in.readLine();
                }
                
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                        clientSocket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
