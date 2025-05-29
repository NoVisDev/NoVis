package com.novis.common.net;

import com.novis.common.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PacketFrameEncoder extends MessageToByteEncoder<Packet> {
    // TODO: MAKE MORE FLEXIBLE

    private static final Logger logger = LoggerFactory.getLogger(PacketFrameDecoder.class);

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) {
        logger.debug("Payload class: " + msg.type);
        logger.debug("Payload serialized length: " + msg.payload.length);
        logger.debug("Raw length in packet: " + msg.rawLength);

        out.writeShort(Packet.MAGIC);

        logger.info("Wrote magic number into the packet.");

        out.writeByte(Packet.VERSION10);

        logger.info("Wrote version byte to packet.");

        out.writeByte(msg.type.id);

        logger.info("Wrote packet type into packet.");

        out.writeInt(msg.rawLength);

        logger.info("Wrote packet length into packet");

        out.writeBytes(msg.payload);

        logger.info("Wrote payload to packet");
    }
}
