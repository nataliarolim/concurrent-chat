package org.academiadecodigo.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientListening implements  Runnable {

    private BufferedReader reader;
    private Socket clientSocket;


    public ClientListening(Socket clientSocket) {
        this.clientSocket = clientSocket;

        try {

            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {


        String messageFromServer;

        try {

            messageFromServer = reader.readLine();

            while (messageFromServer != null) {

                System.out.println(messageFromServer);
                messageFromServer = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        }
    }
