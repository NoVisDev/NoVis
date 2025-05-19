package com.novis.server.net;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RelayServerHandler extends SimpleChannelInboundHandler<String> {
    private static final Logger logger = LoggerFactory.getLogger(RelayServerHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception { // ctx - connection
        // called every time a message is fully decoded
        logger.info("Received: " + msg);
        ctx.writeAndFlush("Echo: " + msg + "\r\n"); // NOTE: delimeter necessary for LineBased
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // close connection on error
        logger.error("error -> ", cause);
        ctx.close();
    }
}
