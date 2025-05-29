package com.novis.server.letterbox;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface LetterboxStorage {
    // Store messages for later view. this is a base class for all types of letterboxes, unencrypted, encrypted...

    Map<String, List<byte[]>> letterboxes = new ConcurrentHashMap<>(); // internal letterboxes

    void storeMessage(String userId, byte[] serializedOrEncrypted) throws SQLException;
    List<byte[]> getMessages(String userId) throws SQLException;
    void deleteMessages(String userId) throws SQLException;
}
