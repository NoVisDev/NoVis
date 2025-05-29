package com.novis.server.net;

import com.novis.client.net.LetterboxClientModule;
import com.novis.common.packet.AcknowledgePacketData;
import com.novis.common.packet.LetterboxPullResponsePacketData;
import com.novis.common.packet.Packet;
import com.novis.common.PacketDirector;
import com.novis.common.packet.PacketData;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PacketServerDirector implements PacketDirector {
    // Direct packets to where they are supposed to be directed on server-side
    // TODO: BASIC FORMATTED PACKETS (ACK, E.T.C)

    private static final Logger logger = LoggerFactory.getLogger(PacketServerDirector.class);

    @Override
    public void directPacketToHandler(ChannelHandlerContext ctx, Packet packet) {
        LetterboxModule lbx_mod = new LetterboxModule();

        switch (packet.type.id) {
            case 1:
                // packet drop off
                lbx_mod.dropOffPacket(packet);
                ctx.writeAndFlush(new Packet((short) 0x4e56, (byte) 0x10, (byte) 3, new AcknowledgePacketData())); // ACK
                break;
            case 2:
                // letterbox pull response
                LetterboxPullResponsePacketData messages = lbx_mod.pickUpLetterbox(packet);
                ctx.writeAndFlush(new Packet((short) 0x4e56, (byte) 0x10, (byte) 4, messages));
                break;
            case 5:
                // letterbox delete response
                lbx_mod.honorDeleteRequest(packet);
                ctx.writeAndFlush(new Packet((short) 0x4e56, (byte) 0x10, (byte) 3, new AcknowledgePacketData())); //
                break;
            default:
                logger.warn("Unknown packet type: {}", packet.type.id);
                break;
        }
    }
}
