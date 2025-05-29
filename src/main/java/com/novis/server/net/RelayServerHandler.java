package com.novis.server.net;

import com.novis.common.packet.Packet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RelayServerHandler extends SimpleChannelInboundHandler<Packet> { // monitors traffic in.
    private static final Logger logger = LoggerFactory.getLogger(RelayServerHandler.class);
    private static final ServerPacketDirector psd = new ServerPacketDirector();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet msg) { // ctx - connection
        // called every time a message is fully decoded
        logger.info("Received: " + msg.toString());

        // packet from handler
        Packet packet = psd.directPacketToHandler(msg);

        if (packet != null) {
            ctx.writeAndFlush(packet);
        } else {
            logger.info("Finished conversation");
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // close connection on error
        logger.error("error -> ", cause);
        ctx.close();
    }
}
