package com.novis.server.net;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.StandardCharsets;

public class RelayServerInitializer extends ChannelInitializer<SocketChannel> { // initialize pipeline for
    // each client.

    @Override
    protected void initChannel(SocketChannel ch) throws Exception { // initializes handlers
        ch.pipeline().addLast(new LineBasedFrameDecoder(1024)); // accept data up to 1024B
        ch.pipeline().addLast(new StringDecoder(StandardCharsets.UTF_8)); // ByteBuf -> String
        ch.pipeline().addLast(new StringEncoder(StandardCharsets.UTF_8)); // our data -> ByteBuf
        ch.pipeline().addLast(new RelayServerHandler()); // our handler
    }
}
