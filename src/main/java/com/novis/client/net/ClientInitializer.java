package com.novis.client.net;

import com.novis.common.net.PacketFrameDecoder;
import com.novis.common.net.PacketFrameEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientInitializer extends ChannelInitializer<SocketChannel> { // pipeline for client.
    private static final Logger logger = LoggerFactory.getLogger(ClientInitializer.class);

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new PacketFrameEncoder()); // decoder for handler
        ch.pipeline().addLast(new PacketFrameDecoder()); // encoder for handler
        ch.pipeline().addLast(new ClientHandler()); // our handler
    }
}
