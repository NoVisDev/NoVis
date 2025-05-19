package com.novis.common;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Message {
    private final String body; // body of our message
    private final String messageId; // message unique id
    private final String senderId; // sender UUID
    private final String recipientId; // same
    private final Instant timestamp; // when message created
    private final boolean encrypted; // for moderation

    public Message(String body, String senderId, String recipientId, boolean encrypted) {
        this.body = body;
        this.messageId = UUID.randomUUID().toString();
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.timestamp = Instant.now();
        this.encrypted = encrypted;
    }

    public String getBody() {
        return body;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public boolean isEncrypted() {
        return encrypted;
    }

    @Override
    public String toString() {
        return "Message{" +
                "body='" + body + '\'' +
                ", messageId='" + messageId + '\'' +
                ", senderId='" + senderId + '\'' +
                ", recipientId='" + recipientId + '\'' +
                ", timestamp=" + timestamp +
                ", encrypted=" + encrypted +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return encrypted == message.encrypted && Objects.equals(body, message.body) && Objects.equals(messageId, message.messageId) && Objects.equals(senderId, message.senderId) && Objects.equals(recipientId, message.recipientId) && Objects.equals(timestamp, message.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(body, messageId, senderId, recipientId, timestamp, encrypted);
    }
}
