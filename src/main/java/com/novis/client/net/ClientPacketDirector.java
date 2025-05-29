package com.novis.client.net;

import com.novis.common.PacketDirector;
import com.novis.common.packet.LetterboxDeleteRequestPacketData;
import com.novis.common.packet.Packet;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientPacketDirector implements PacketDirector {
    private static final Logger logger = LoggerFactory.getLogger(ClientPacketDirector.class);

    @Override
    public void directPacketToHandler(ChannelHandlerContext ctx, Packet packet) {
        LetterboxClientModule clientModule = new LetterboxClientModule();

        switch (packet.type.id) {
            case 3:
                logger.info("Packet (ACK): {}", packet);
                break;
            case 4:
                logger.info("Receiving messages and sending back delete request.");
                logger.info("MESSAGE DATA ==========================");
                String recipientIdToClear = clientModule.displayMessages(packet);
                logger.info("MESSAGE DATA END ======================");

                logger.info("Sending back delete request.");
                LetterboxDeleteRequestPacketData ldrpd = new LetterboxDeleteRequestPacketData(recipientIdToClear);

                ctx.writeAndFlush(new Packet((short) 0x4e56, (byte) 0x10, (byte) 5, ldrpd));
                break;
        }
    }
}
