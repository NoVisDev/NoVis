package com.novis.common.serializer;

import com.novis.common.packet.LetterboxPullResponsePacketData;

public class LPRPSerializer implements PacketSerializer<LetterboxPullResponsePacketData> {

    @Override
    public byte[] serialize(LetterboxPullResponsePacketData data) {
        return SerializationHelper.serialize(data);
    }

    @Override
    public LetterboxPullResponsePacketData deserialize(byte[] bytes) {
        return (LetterboxPullResponsePacketData) SerializationHelper.deserialize(bytes);
    }
}
