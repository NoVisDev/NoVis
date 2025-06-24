package com.novis.common.packet.data;

import java.util.Arrays;

public class KeyExchangePacketData extends PacketData {
    private byte[] publicKey; // public key to send over for key exchange
    private String toExchangeWith;
    private String from;

    public KeyExchangePacketData(byte[] publicKey, String toExchangeWith, String from) {
        this.publicKey = publicKey;
        this.toExchangeWith = toExchangeWith;
        this.from = from;
    }

    public KeyExchangePacketData() {};

    public String getFrom() {
        return from;
    }

    public byte[] getPublicKey() {
        return publicKey;
    }

    public String getToExchangeWith() {
        return toExchangeWith;
    }

    @Override
    public String toString() {
        return "KeyExchangePacketData{" +
                "publicKey=" + Arrays.toString(publicKey) +
                ", toExchangeWith='" + toExchangeWith + '\'' +
                ", from='" + from + '\'' +
                '}';
    }
}
