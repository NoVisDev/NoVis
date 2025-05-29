package com.novis;

import com.novis.server.net.RelayServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        // Run function for NoVis (RUNS YOUR SERVER)

        logger.info("NoVis instance/test started from class Main's static function main");
        logger.info("Built successfully");

        RelayServer rs = new RelayServer(9000);
        rs.start();
    }
}