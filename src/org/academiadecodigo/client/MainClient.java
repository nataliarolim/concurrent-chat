package org.academiadecodigo.client;

import java.io.IOException;

public class MainClient {

    public static void main(String[] args) throws IOException {


        Client client = new Client("localhost",8000 );
        client.connection();

    }
}