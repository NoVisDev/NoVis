package com.novis.client.user;

import com.novis.client.net.Client;

import java.util.Random;

public class User {
    private final int id = new Random().nextInt(Integer.MAX_VALUE); // HACK: need to implement proper user IDs

    public int getId() {
        return id;
    }

    public void connectToServer(String host, int port) throws Exception {
        // connect to a server using the Client

        Client userConnection = new Client(host, port);

        userConnection.run();
    }
}
