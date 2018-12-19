package org.academiadecodigo.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private ServerSocket serverSocket;
    private List<ClientHandle> clientHandles;
    private ExecutorService fixedPoll;

    public Server(int portNumber) throws IOException {
            clientHandles = Collections.synchronizedList(new LinkedList<>());
            serverSocket = new ServerSocket(portNumber);
            fixedPoll = Executors.newFixedThreadPool(200);

    }

    public void connectionToClient() {

        int counter = 0;

        while (true) {

            try {
                Socket clientSocket = serverSocket.accept();
                counter++;

                ClientHandle clientHandle = new ClientHandle("client " + counter, clientSocket, this);//representar a ligação entre o servidor e o client
                clientHandles.add(clientHandle);

                fixedPoll.submit(clientHandle);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    public void broadcast(String sendAll, ClientHandle client) {

        synchronized (clientHandles) {

            for (ClientHandle ch : clientHandles) {

                ch.send(client.getClientName() + ": " + sendAll);
            }

        }
    }

    public void removedSocket(ClientHandle client) {

        clientHandles.remove(client);
    }

}






