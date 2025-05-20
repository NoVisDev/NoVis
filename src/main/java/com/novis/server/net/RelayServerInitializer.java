package com.novis.server.net;

import com.novis.common.PacketFrameDecoder;
import com.novis.common.PacketFrameEncoder;
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
        ch.pipeline().addLast(new PacketFrameDecoder()); // ByteBuf -> Packet
        ch.pipeline().addLast(new PacketFrameEncoder()); // our packet -> ByteBuf
        ch.pipeline().addLast(new RelayServerHandler()); // our handler
    }
}
