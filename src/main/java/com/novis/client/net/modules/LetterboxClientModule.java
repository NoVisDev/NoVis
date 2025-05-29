package com.novis.client.net.modules;

import com.novis.common.net.Message;
import com.novis.common.packet.data.PacketData;
import com.novis.common.serializer.SerializationHelper;
import com.novis.common.packet.data.LetterboxPullResponsePacketData;
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
