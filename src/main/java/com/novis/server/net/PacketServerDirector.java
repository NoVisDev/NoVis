package com.novis.server.net;

import com.novis.common.Packet;
import com.novis.common.PacketDirector;
import com.novis.common.PacketType;
import io.netty.channel.ChannelHandlerContext;

import java.nio.charset.StandardCharsets;

public class PacketServerDirector implements PacketDirector {
    // Direct packets to where they are supposed to be directed on server-side
    // TODO: BASIC FORMATTED PACKETS (ACK, E.T.C)

    @Override
    public void directPacketToHandler(ChannelHandlerContext ctx, Packet packet) {
        LetterboxModule lbx_mod = new LetterboxModule();

        switch (packet.type.id) {
            case 1:
                // packet drop off
                lbx_mod.dropOffPacket(packet);
                ctx.writeAndFlush(new Packet((short) 0x4e56, (byte) 0x10, (byte) 3, 3, Packet.ACK)); // ACK
        }
    }
}
