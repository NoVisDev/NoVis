package com.novis.client.net;

import com.novis.common.PacketFrameDecoder;
import com.novis.common.PacketFrameEncoder;
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
        ch.pipeline().addLast(new PacketFrameEncoder()); // decoder for handler
        ch.pipeline().addLast(new PacketFrameDecoder()); // encoder for handler
        ch.pipeline().addLast(new ClientHandler()); // our handler
    }
}
