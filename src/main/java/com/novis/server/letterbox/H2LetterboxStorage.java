package com.novis.server.letterbox;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class H2LetterboxStorage extends LocalDBLetterboxStorage {

    public H2LetterboxStorage(){
        super(); // blank
    }

    public void initObject() throws SQLException {
        conn = DriverManager.getConnection("jdbc:h2:mem:novis;DB_CLOSE_DELAY=-1");
        initDatabase();
    }

    private void initDatabase() throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS messages (" +
                    "message_id IDENTITY PRIMARY KEY, " +
                    "recipient_id VARCHAR(255) NOT NULL, " +
                    "body_blob BLOB NOT NULL, " + // or BLOB if you store binary
                    "timestamp BIGINT NOT NULL" +
                    ");");
        }
    }

}
