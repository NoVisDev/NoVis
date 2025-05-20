package com.novis.client.net;

import com.novis.common.Packet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientHandler extends SimpleChannelInboundHandler<Packet> {
    // TODO: CREATE PROPER TEXT BASED UI

    private static final Logger logger = LoggerFactory.getLogger(ClientHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
        logger.info("Server reads: " + msg.toString()); // just simply echo what the server sends back
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error("error ->", cause);
        ctx.close(); // close connection if error
    }
}
