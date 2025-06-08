package com.novis.server.net;

import com.novis.common.net.PacketBuilder;
import com.novis.common.packet.PacketType;
import com.novis.common.packet.data.AcknowledgePacketData;
import com.novis.common.packet.data.LetterboxPullResponsePacketData;
import com.novis.common.packet.Packet;
import com.novis.common.net.PacketDirector;
import com.novis.common.packet.data.PacketData;
import com.novis.server.net.modules.LetterboxModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerPacketDirector implements PacketDirector {
    // Direct packets to where they are supposed to be directed on server-side
    // TODO: BASIC FORMATTED PACKETS (ACK, E.T.C)

    private static final Logger logger = LoggerFactory.getLogger(ServerPacketDirector.class);

    @Override
    public Packet directPacketToHandler(Packet packet) {
        LetterboxModule lbx_mod = new LetterboxModule();

        PacketType type = packet.type;
        PacketData data = PacketBuilder.deserialize(type, packet.payload);

        switch (packet.type.id) { // note for any contributors, we cannot avoid using numbers.
            case 1 -> {
                // packet drop off
                lbx_mod.dropOffPacket(data);
                return (PacketBuilder.buildPacket(PacketType.ACKNOWLEDGE, new AcknowledgePacketData()));
            }

            // NOTE: Keeping ACK packet for future use in the architecture
            case 2 -> {
                // letterbox pull response
                LetterboxPullResponsePacketData messages = lbx_mod.pickUpLetterbox(data);
                return (PacketBuilder.buildPacket(PacketType.LETTERBOX_PULL_RESPONSE, messages));
            }
            case 5 -> {
                // letterbox delete response
                lbx_mod.honorDeleteRequest(data);
                return (PacketBuilder.buildPacket(PacketType.ACKNOWLEDGE, new AcknowledgePacketData()));
            }
            default -> logger.warn("Unknown packet type: {}", packet.type.id);
        }

        return null;
    }
}
