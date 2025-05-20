package com.novis.server.net;

import com.novis.common.Packet;
import com.novis.server.letterbox.LocalDBLetterboxStorage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Base64;

public class RelayServerHandler extends SimpleChannelInboundHandler<Packet> { // monitors traffic in.
    private static final Logger logger = LoggerFactory.getLogger(RelayServerHandler.class);
    private static final PacketServerDirector psd = new PacketServerDirector();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception { // ctx - connection
        // called every time a message is fully decoded
        logger.info("Received: " + msg.toString());

        // simply monitor
        psd.directPacketToHandler(ctx, msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // close connection on error
        logger.error("error -> ", cause);
        ctx.close();
    }
}
