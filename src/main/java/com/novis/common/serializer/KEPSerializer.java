package com.novis.common.serializer;

import com.novis.common.packet.data.KeyExchangePacketData;

public class KEPSerializer implements PacketSerializer<KeyExchangePacketData> {
    @Override
    public byte[] serialize(KeyExchangePacketData data) {
        return SerializationHelper.serialize(data);
    }

    @Override
    public KeyExchangePacketData deserialize(byte[] bytes) {
        return (KeyExchangePacketData) SerializationHelper.deserialize(bytes);
    }
}
