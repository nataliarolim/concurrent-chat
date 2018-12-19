package org.academiadecodigo.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket clientSocket;
    private PrintWriter writer;


    public Client(String hostName, int portNumber) throws IOException {

        clientSocket = new Socket(hostName, portNumber);
        System.out.println(" Connected to: " + clientSocket.getRemoteSocketAddress());

    }

   public void sendMessage() {

       String sendMessage;

       try {
           writer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

           Scanner scanner = new Scanner(System.in);

               sendMessage = scanner.nextLine();
               writer.println(sendMessage);

       } catch (IOException e) {
           e.printStackTrace();
       }

   }

    public void connection() {

        Thread thread = new Thread(new ClientListening(clientSocket));
        thread.start();

            while(true) {

                sendMessage();
            }

    }


   /* private void closeSocket(){

            try {

                if(clientSocket != null) {
                    System.out.println("Closing the socket ");
                    clientSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }




