package com.novis.common.net;

import com.novis.common.packet.Packet;
import io.netty.channel.ChannelHandlerContext;

public interface PacketDirector { // base class for packet directors
    Packet directPacketToHandler(Packet packet);
}
