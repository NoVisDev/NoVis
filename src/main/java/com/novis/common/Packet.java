package com.novis.common;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Packet {
    /*
    * Packet structure =
    * [Magic 2B] [Version 1B] [Type 1B] [Payload Length 4B] [Payload N bytes]
    * Magic = 0x4E56 / NV
    * Version format = last 4 bits (minor version)
    *                   first 4 bits (major version)
    * Types as of version beta 1.0 =
    *
    * 0 - Message drop off / to letterbox
    * 1 - Message download
    * */

    // TODO: READY MADE PACKET FORMATS

    public static final byte[] ACK = "ACK".getBytes(StandardCharsets.UTF_8); // ACK TEXT PAYLOAD

    public PacketType type;
    public byte[] payload;

    // packet headers
    public byte version;
    public byte rawType;
    public int rawLength;
    public short magic;

    public Packet(short magic, byte version, byte rawType, int rawLength, byte[] payload) {
        this.payload = payload;
        this.version = version;
        this.rawType = rawType;
        this.rawLength = rawLength;
        this.magic = magic;

        this.type = PacketType.fromId(rawType);
    }

    @Override
    public String toString() {
        return "Packet{" +
                "type=" + type +
                ", payload=" + Arrays.toString(payload) +
                ", version=" + version +
                ", rawType=" + rawType +
                ", rawLength=" + rawLength +
                ", magic=" + magic +
                '}';
    }
}
