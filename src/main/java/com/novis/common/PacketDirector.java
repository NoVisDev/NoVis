package com.novis.common;

import io.netty.channel.ChannelHandlerContext;

public interface PacketDirector { // base class for packet directors
    void directPacketToHandler(ChannelHandlerContext ctx, Packet packet);
}
