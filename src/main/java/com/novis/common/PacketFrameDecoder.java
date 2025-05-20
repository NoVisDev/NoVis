package com.novis.common;

import com.novis.common.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PacketFrameDecoder extends ByteToMessageDecoder {
    private static final Logger logger = LoggerFactory.getLogger(PacketFrameDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 8) return;

        in.markReaderIndex();

        short magic = in.readShort();

        logger.info("Reading packet magic -> " + Short.toString(magic));

        if (magic != (short) 0x4E56) {
            logger.error("Packet doesn't match correct magic number -> " + Short.toString(magic) + " != 0x4e56 / 20054");

            ctx.close();
            return;
        }

        byte version = in.readByte();
        byte typeByte = in.readByte();
        int length = in.readInt();

        logger.info("Reading packet headers -> " + Byte.toString(version)
                + ", " + Byte.toString(typeByte)
                + ", " + Integer.toString(length));

        if (in.readableBytes() < length) {
            logger.error("Packet length header mismatch (" + Integer.toString(in.readableBytes()) + "!=" + Integer.toString(length) + "). Stopping packet.");

            in.resetReaderIndex();
            return;
        }

        byte[] payload = new byte[length];
        in.readBytes(payload);

        out.add(new Packet(magic, version, typeByte, length, payload));
    }
}
