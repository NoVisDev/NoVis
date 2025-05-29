package com.novis.client.net;

import com.novis.common.packet.Packet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientHandler extends SimpleChannelInboundHandler<Packet> {
    // TODO: CREATE PROPER TEXT BASED UI

    private static final Logger logger = LoggerFactory.getLogger(ClientHandler.class);
    private static final ClientPacketDirector cpd = new ClientPacketDirector();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet msg) {
        logger.info("Server reads: " + msg.toString()); // just simply echo what the server sends back

        cpd.directPacketToHandler(ctx, msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error("error ->", cause);
        ctx.close(); // close connection if error
    }
}
