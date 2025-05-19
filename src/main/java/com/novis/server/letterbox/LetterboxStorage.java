package com.novis.server.letterbox;

import com.novis.common.Message;

public interface LetterboxStorage {
    // Store messages for later view. this is a base class for all types of letterboxes, unencrypted, encrypted...

    void storeMessage(String userId, Message msg);
}
