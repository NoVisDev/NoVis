package com.novis.common;

import com.novis.common.packet.Packet;
import io.netty.channel.ChannelHandlerContext;

public interface PacketDirector { // base class for packet directors
    Packet directPacketToHandler(Packet packet);
}
