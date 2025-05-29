package com.novis.client.net;

import com.novis.client.net.modules.LetterboxClientModule;
import com.novis.common.net.PacketBuilder;
import com.novis.common.net.PacketDirector;
import com.novis.common.packet.PacketType;
import com.novis.common.packet.data.LetterboxDeleteRequestPacketData;
import com.novis.common.packet.Packet;
import com.novis.common.packet.data.PacketData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientPacketDirector implements PacketDirector {
    private static final Logger logger = LoggerFactory.getLogger(ClientPacketDirector.class);

    @Override
    public Packet directPacketToHandler(Packet packet) {
        LetterboxClientModule clientModule = new LetterboxClientModule();

        PacketType type = packet.type;
        PacketData data = PacketBuilder.deserialize(type, packet.payload);

        switch (packet.type.id) {
            case 3 -> logger.info("Packet (ACK): {}", packet);
            case 4 -> {
                logger.info("Receiving messages and sending back delete request.");
                logger.info("MESSAGE DATA ==========================");
                String recipientIdToClear = clientModule.displayMessages(data);
                logger.info("MESSAGE DATA END ======================");
                logger.info("Sending back delete request.");
                LetterboxDeleteRequestPacketData ldrpd = new LetterboxDeleteRequestPacketData(recipientIdToClear);
                return PacketBuilder.buildPacket(PacketType.LETTERBOX_DELETE_REQUEST, ldrpd);
            }
        }

        return null;
    }
}
