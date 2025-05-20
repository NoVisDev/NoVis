package com.novis;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.novis.common.Message;
import com.novis.common.SerializationHelper;
import com.novis.server.letterbox.H2LetterboxStorage;
import com.novis.server.letterbox.InMemoryLetterboxStorage;
import com.novis.server.letterbox.LocalDBLetterboxStorage;
import com.novis.server.net.RelayServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Base64;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        // Run function for NoVis

        logger.info("NoVis instance/test started from class Main's static function main");
        logger.info("Built successfully");
    }
}