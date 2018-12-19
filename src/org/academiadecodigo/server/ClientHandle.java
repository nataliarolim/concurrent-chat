package org.academiadecodigo.server;

import org.academiadecodigo.server.Server;

import java.io.*;
import java.net.Socket;

public class ClientHandle implements Runnable {

    private Server server;
    private Socket client;
    private BufferedReader reader;
    private PrintWriter writer;
    private String clientName;


    public ClientHandle(String clientName,Socket client, Server server) throws IOException {
        this.clientName = clientName;
        this.server = server;
        this.client = client;
        reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    @Override
    public void run() {

        String messageFromClient;

        try {

            messageFromClient = reader.readLine();

            while (messageFromClient != null) {

                System.out.println(getClientName() + " : " + messageFromClient);

                server.broadcast(messageFromClient, this);

                if(messageFromClient.equals("/quit")){
                    break;
                }

                messageFromClient = reader.readLine();

            }
            closeSocket();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

    }


    public void send(String message) {

        try {
            writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);
            writer.println(message);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void closeSocket() {

        try {
            server.removedSocket(this);

            System.out.println( getClientName() + " closed ");
            client.close();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }


}

