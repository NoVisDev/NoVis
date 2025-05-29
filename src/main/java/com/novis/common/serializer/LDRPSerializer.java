package com.novis.common.serializer;

import com.novis.common.packet.LetterboxDeleteRequestPacketData;

public class LDRPSerializer implements PacketSerializer<LetterboxDeleteRequestPacketData> {
    @Override
    public byte[] serialize(LetterboxDeleteRequestPacketData data) {
        return SerializationHelper.serialize(data);
    }

    @Override
    public LetterboxDeleteRequestPacketData deserialize(byte[] bytes) {
        return (LetterboxDeleteRequestPacketData) SerializationHelper.deserialize(bytes);
    }
}
