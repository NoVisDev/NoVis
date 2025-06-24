package com.novis;

import com.novis.client.net.Client;
import com.novis.crypto.Crypto;
import com.novis.tests.TestClientLetterboxPull;
import com.novis.tests.TestClientMessageSend;
import com.novis.tests.TestClientReceiveKEP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientTestClass { // Main equivalent for the Client
    private static final Logger logger = LoggerFactory.getLogger(ClientTestClass.class);

    public static void main(String[] args) throws Exception {
        Crypto.init();

        logger.info("Starting the client");

        Client client = new Client("localhost", 9000);
        client.run(new TestClientReceiveKEP());
    }
}
