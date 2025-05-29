package com.novis.common.serializer;

import com.novis.common.packet.AcknowledgePacketData;

public class AcknowledgePacketSerializer implements PacketSerializer<AcknowledgePacketData> {
    @Override
    public byte[] serialize(AcknowledgePacketData data) {
        return SerializationHelper.serialize(data);
    }

    @Override
    public AcknowledgePacketData deserialize(byte[] bytes) {
        return (AcknowledgePacketData) SerializationHelper.deserialize(bytes);
    }
}
