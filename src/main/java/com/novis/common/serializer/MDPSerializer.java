package com.novis.common.serializer;

import com.novis.common.packet.MessageDeliveryPacketData;

public class MDPSerializer implements PacketSerializer<MessageDeliveryPacketData> {
    @Override
    public byte[] serialize(MessageDeliveryPacketData data) {
        return SerializationHelper.serialize(data);
    }

    @Override
    public MessageDeliveryPacketData deserialize(byte[] bytes) {
        return (MessageDeliveryPacketData) SerializationHelper.deserialize(bytes);
    }
}
