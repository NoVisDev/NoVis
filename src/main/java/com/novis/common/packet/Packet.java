package com.novis.common.packet;

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

    public static final short MAGIC = 0x4E56;
    public static final byte VERSION10 = 0x10;

    // TODO: READY MADE PACKET FORMATS

    public PacketType type;
    public byte[] payload;

    // packet headers
    public byte version;
    public byte rawType;
    public int rawLength;
    public short magic;

    public Packet(short magic, byte version, byte rawType, byte[] payload) {
        this.payload = payload;

        this.version = version;
        this.rawType = rawType;
        this.rawLength = payload.length;
        this.magic = magic;

        this.type = PacketType.fromId(rawType);
    }

    @Override
    public String toString() {
        return "Packet{" +
                "type=" + type +
                ", payload=" + payload.toString() +
                ", version=" + version +
                ", rawType=" + rawType +
                ", rawLength=" + rawLength +
                ", magic=" + magic +
                '}';
    }
}
