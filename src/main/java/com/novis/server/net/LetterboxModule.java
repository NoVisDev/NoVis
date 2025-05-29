package com.novis.server.net;

import ch.qos.logback.core.encoder.ByteArrayUtil;
import com.novis.common.Message;
import com.novis.common.packet.*;
import com.novis.common.SerializationHelper;
import com.novis.server.letterbox.LocalDBLetterboxStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
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

        MessageDeliveryPacketData mdpd = (MessageDeliveryPacketData) message.payload;
        Message messageObject = (Message) SerializationHelper.deserialize(mdpd.getMessagePayload()); // retrieve our object

        logger.info("Received packet and extracted message -> " + messageObject);

        try {
            ldbls.storeMessage(mdpd.getRecipientId(), mdpd.getMessagePayload());
        } catch (Exception e) {
            logger.error("error -> ", e);
        }

        logger.info("Stored to the database");
    }

    public LetterboxPullResponsePacketData pickUpLetterbox(Packet request) {
        // pickup letterbox

        LetterboxPullPacketData lppd = (LetterboxPullPacketData) request.payload;
        String recipientId = lppd.getRecipientId();

        logger.info("Received packet and extracted message -> " + lppd);

        try {
            List<byte[]> extracted = ldbls.getMessages(recipientId);
            byte[][] messages = new byte[extracted.size()][];

            for (int i = 0; i < messages.length; i++) {
                messages[i] = extracted.get(i).clone();
            }

            return new LetterboxPullResponsePacketData(messages, recipientId);
        } catch (Exception e) {
            logger.error("error -> ", e);
        }

        return null;
    }

    public void honorDeleteRequest(Packet request) {
        // delete letterbox contents

        LetterboxDeleteRequestPacketData ldrpd = (LetterboxDeleteRequestPacketData) request.payload;
        String recipientId = ldrpd.getRecipientId();

        try {
            ldbls.deleteMessages(recipientId);
        } catch (Exception e) {
            logger.error("error -> ", e);
        }
    }
}
