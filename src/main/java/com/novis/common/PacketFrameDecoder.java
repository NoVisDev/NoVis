package com.novis.common;

import com.novis.common.packet.*;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PacketFrameDecoder extends ByteToMessageDecoder {
    private static final Logger logger = LoggerFactory.getLogger(PacketFrameDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        if (in.readableBytes() < 8) return;

        in.markReaderIndex();

        short magic = in.readShort();

        logger.info("Reading packet magic -> " + magic);

        if (magic != (short) 0x4E56) {
            logger.error("Packet doesn't match correct magic number -> " + magic + " != 0x4e56 / 20054");

            ctx.close();
            return;
        }

        byte version = in.readByte();
        byte typeByte = in.readByte();
        int length = in.readInt();

        logger.info("Reading packet headers -> " + version
                + ", " + typeByte
                + ", " + length);

        if (in.readableBytes() < length) {
            logger.error("Packet length header mismatch (" + in.readableBytes() + "!=" + length + "). Stopping packet.");

            in.resetReaderIndex();
            return;
        }

        byte[] payload = new byte[length];
        in.readBytes(payload);

        Object deserialized = SerializationHelper.deserialize(payload);

        switch (typeByte) {
            case 1: // message delivery
                out.add(new Packet(magic, version, typeByte, (MessageDeliveryPacketData) deserialized));
                break;
            case 2: // letterbox pulling
                out.add(new Packet(magic, version, typeByte, (LetterboxPullPacketData) deserialized));
                break;
            case 3: // ack
                out.add(new Packet(magic, version, typeByte, (AcknowledgePacketData) deserialized));
                break;
            case 4:
                out.add(new Packet(magic, version, typeByte, (LetterboxPullResponsePacketData) deserialized));
                break;
            case 5:
                out.add(new Packet(magic, version, typeByte, (LetterboxDeleteRequestPacketData) deserialized));
                break;
            default:
                logger.warn("Unknown packet type: " + typeByte);
        }
    }
}
