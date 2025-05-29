package com.novis.server.letterbox;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InMemoryLetterboxStorage implements LetterboxStorage {
    // HACK: for ease of development speed
    // NOTE: DEPRECATED

    @Override
    public void storeMessage(String userId, byte[] serializedMsg) throws SQLException {
        if (!letterboxes.containsKey(userId)) {
            letterboxes.put(userId, new ArrayList<byte[]>());
        }
        letterboxes.get(userId).add(serializedMsg);
    }

    @Override
    public List<byte[]> getMessages(String userId) throws SQLException {
        return letterboxes.getOrDefault(userId, null);
    }

    @Override
    public void deleteMessages(String userId) throws SQLException {
        letterboxes.remove(userId);
    }
}
