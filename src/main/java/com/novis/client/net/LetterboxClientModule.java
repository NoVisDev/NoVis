package com.novis.client.net;

import com.novis.common.Message;
import com.novis.common.packet.PacketData;
import com.novis.common.serializer.SerializationHelper;
import com.novis.common.packet.LetterboxPullResponsePacketData;
import com.novis.common.packet.Packet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LetterboxClientModule {
    private static final Logger logger = LoggerFactory.getLogger(LetterboxClientModule.class);

    public String displayMessages(PacketData messageDataPacket) {
        LetterboxPullResponsePacketData lprpd = (LetterboxPullResponsePacketData) messageDataPacket;
        byte[][] serializedMessageArray = lprpd.getMessageData();

        Message[] messages = new Message[serializedMessageArray.length];

        for (int i = 0; i < serializedMessageArray.length; i++) {
            messages[i] = (Message) SerializationHelper.deserialize(serializedMessageArray[i]);
        }

        for (Message message: messages) {
            logger.info("Body: {} \n Sender ID: {} \n Timestamp: {}", message.getBody(),
                    message.getSenderId(),
                    message.getTimestamp());
            // log messages
        }

        return lprpd.getRecipientId();
    }
}
