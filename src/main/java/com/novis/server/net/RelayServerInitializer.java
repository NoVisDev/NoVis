package com.novis.server.net;

import com.novis.common.net.PacketFrameDecoder;
import com.novis.common.net.PacketFrameEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class RelayServerInitializer extends ChannelInitializer<SocketChannel> { // initialize pipeline for
    // each client.

    @Override
    protected void initChannel(SocketChannel ch) throws Exception { // initializes handlers
        ch.pipeline().addLast(new PacketFrameDecoder()); // ByteBuf -> Packet
        ch.pipeline().addLast(new PacketFrameEncoder()); // our packet -> ByteBuf
        ch.pipeline().addLast(new RelayServerHandler()); // our handler
    }
}
