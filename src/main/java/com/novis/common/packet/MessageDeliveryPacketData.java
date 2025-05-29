package com.novis.common.packet;

import java.util.Arrays;

public class MessageDeliveryPacketData extends PacketData {
    private String recipientId;
    private byte[] messagePayload;

    public MessageDeliveryPacketData(String recipientId, byte[] messagePayload) {
        this.recipientId = recipientId;
        this.messagePayload = messagePayload;
    }

    public MessageDeliveryPacketData() {}

    public byte[] getMessagePayload() {
        return messagePayload;
    }

    public String getRecipientId() {
        return recipientId;
    }

    @Override
    public String toString() {
        return "MessageDeliveryPacketData{" +
                "recipientId='" + recipientId + '\'' +
                ", messagePayload=" + Arrays.toString(messagePayload) +
                '}';
    }
}
