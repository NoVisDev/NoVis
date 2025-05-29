package com.novis.common.packet;

public class LetterboxPullPacketData extends PacketData {
    private String recipientId;

    public LetterboxPullPacketData(String recipientId) {
        this.recipientId = recipientId;
    }

    public LetterboxPullPacketData() {}

    @Override
    public String toString() {
        return "LetterboxPullPacketData{" +
                "recipientId='" + recipientId + '\'' +
                '}';
    }

    public String getRecipientId() {
        return recipientId;
    }
}
