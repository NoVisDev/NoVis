package com.novis.common.serializer;

import com.novis.common.packet.LetterboxPullPacketData;

public class LPPSerializer implements PacketSerializer<LetterboxPullPacketData> {
    @Override
    public byte[] serialize(LetterboxPullPacketData data) {
        return SerializationHelper.serialize(data);
    }

    @Override
    public LetterboxPullPacketData deserialize(byte[] bytes) {
        return (LetterboxPullPacketData) SerializationHelper.deserialize(bytes);
    }
}
