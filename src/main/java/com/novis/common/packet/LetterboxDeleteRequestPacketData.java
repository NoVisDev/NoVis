package com.novis.common.packet;

public class LetterboxDeleteRequestPacketData extends PacketData {
    private String recipientId;

    public LetterboxDeleteRequestPacketData(String recipientId) {
        this.recipientId = recipientId;
    }

    public LetterboxDeleteRequestPacketData() {}

    @Override
    public String toString() {
        return "LetterboxDeleteRequestPacketData{" +
                "recipientId='" + recipientId + '\'' +
                '}';
    }

    public String getRecipientId() {
        return recipientId;
    }
}
