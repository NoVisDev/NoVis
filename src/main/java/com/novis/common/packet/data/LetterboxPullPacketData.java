package com.novis.common.packet.data;

import com.novis.common.packet.data.PacketData;

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
