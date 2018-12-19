package org.academiadecodigo.server;

import java.io.IOException;

public class MainServer {

    public static void main(String[] args) {


        Server server = null;
        try {
            server = new Server(8000);
            server.connectionToClient();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
