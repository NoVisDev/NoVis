package com.novis.common.net;

import com.novis.common.packet.Packet;
import com.novis.common.packet.PacketType;
import com.novis.common.packet.data.PacketData;
import com.novis.common.serializer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class PacketBuilder {
    private static final Map<PacketType, PacketSerializer<? extends PacketData>> serializers = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(PacketBuilder.class);

    static {
        serializers.put(PacketType.MESSAGE, new MDPSerializer());
        serializers.put(PacketType.LETTERBOX_PULL, new LPPSerializer());
        serializers.put(PacketType.ACKNOWLEDGE, new AcknowledgePacketSerializer());
        serializers.put(PacketType.LETTERBOX_PULL_RESPONSE, new LPRPSerializer());
        serializers.put(PacketType.LETTERBOX_DELETE_REQUEST, new LDRPSerializer());
        serializers.put(PacketType.KEY_EXCHANGE, new KEPSerializer());
    }

    public static <T extends PacketData> Packet buildPacket(PacketType type, T data) {
        @SuppressWarnings("unchecked")
                PacketSerializer<T> serializer = (PacketSerializer<T>) serializers.get(type);
        if (serializer == null) logger.error("No serializers: " + type, new IllegalArgumentException());

        byte[] payload = serializer.serialize(data);
        return new Packet((short) Packet.MAGIC, (byte) Packet.VERSION10, type.id, payload);
    }

    public static PacketData deserialize(PacketType type, byte[] payload) {
        PacketSerializer<? extends PacketData> serializer = serializers.get(type);
        if (serializer == null) logger.error("No serializers: " + type, new IllegalArgumentException());
        return serializer.deserialize(payload);
    }
}
