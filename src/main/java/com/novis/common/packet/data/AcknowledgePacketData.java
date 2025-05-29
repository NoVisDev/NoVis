package com.novis.common.packet.data;

import com.novis.common.packet.data.PacketData;

import java.util.Arrays;

public class AcknowledgePacketData extends PacketData {
    private final byte[] payload = new byte[] {(byte) 'A', (byte) 'C', (byte) 'K'};

    public AcknowledgePacketData() {}

    @Override
    public String toString() {
        return "AcknowledgePacketData{" +
                "payload=" + Arrays.toString(payload) +
                '}';
    }
}
