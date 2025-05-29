package com.novis.common.packet;

import java.util.Arrays;

public class LetterboxPullResponsePacketData extends PacketData{
    private byte[][] messageData;
    private String recipientId;

    public LetterboxPullResponsePacketData(byte[][] messageData, String recipientId) {
        this.messageData = messageData;
        this.recipientId = recipientId;
    }

    public LetterboxPullResponsePacketData() {}

    @Override
    public String toString() {
        return "LetterboxPullResponsePacketData{" +
                "messageData=" + Arrays.toString(messageData) +
                ", recipientId='" + recipientId + '\'' +
                '}';
    }

    public byte[][] getMessageData() {
        return messageData;
    }

    public String getRecipientId() {
        return recipientId;
    }
}
