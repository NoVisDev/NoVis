package com.novis;

import com.novis.common.Message;
import com.novis.common.SerializationHelper;
import com.novis.server.net.RelayServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        // Run function for NoVis

        logger.info("NoVis instance/test started from class Main's static function main");
        logger.info("Built successfully");

        // FIXME: Serialization does not work
        Message message = new Message("hello", "ax", "bx", true);
        byte[] array = SerializationHelper.serializeMessage(message);
        System.out.println(new String(array, StandardCharsets.UTF_8));
        Message deserialized = SerializationHelper.deserializeMessage(array);
        System.out.println(deserialized.toString());

        // RelayServer r = new RelayServer(9000);
        // r.start();
    }
}