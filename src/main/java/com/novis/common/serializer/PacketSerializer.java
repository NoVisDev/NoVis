package com.novis.common.serializer;

import com.novis.common.packet.PacketData;

public interface PacketSerializer<T extends PacketData> {
    byte[] serialize(T data);
    T deserialize(byte[] bytes);
}
