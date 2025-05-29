package com.novis.common.serializer;

import com.novis.common.packet.data.PacketData;

public interface PacketSerializer<T extends PacketData> {
    byte[] serialize(T data);
    T deserialize(byte[] bytes);
}
