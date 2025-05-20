package com.novis.server.net;

import com.novis.common.Message;
import com.novis.common.Packet;
import com.novis.common.SerializationHelper;
import com.novis.server.letterbox.LocalDBLetterboxStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class LetterboxModule {
    // runs letterbox software

    // NOTE: PRETTY SIMPLE STORE
    // TODO: ADD PICKUP

    private static final LocalDBLetterboxStorage ldbls = new LocalDBLetterboxStorage();

    private static final Logger logger = LoggerFactory.getLogger(LetterboxModule.class);

    public LetterboxModule() {
        try {
            ldbls.initObject("data/novis.db");
        } catch (Exception e) {
            logger.error("error -> ", e);
        }
    }

    public void dropOffPacket(Packet message) {
        // drop off message, no need for the ctx, server director sends back an ACK for this pathway

        Message messageObject = (Message) SerializationHelper.deserialize(message.payload); // retrieve our object

        logger.info("Received packet and extracted message -> " + messageObject.toString());

        try {
            ldbls.storeMessage(messageObject.getRecipientId(), message.payload);
        } catch (Exception e) {
            logger.error("error -> ", e);
        }

        logger.info("Stored to the database");
    }
}
