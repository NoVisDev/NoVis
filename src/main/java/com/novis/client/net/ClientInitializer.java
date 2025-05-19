package com.novis.client.net;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public class ClientInitializer extends ChannelInitializer<SocketChannel> { // pipeline for client.
    private static final Logger logger = LoggerFactory.getLogger(ClientInitializer.class);

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new LineBasedFrameDecoder(1024)); // bare bones decoder
        ch.pipeline().addLast(new StringDecoder(StandardCharsets.UTF_8)); // decoder for handler
        ch.pipeline().addLast(new StringEncoder(StandardCharsets.UTF_8)); // encoder for handler
        ch.pipeline().addLast(new ClientHandler()); // our handler
    }
}
