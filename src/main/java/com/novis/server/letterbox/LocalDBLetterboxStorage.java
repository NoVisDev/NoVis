package com.novis.server.letterbox;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocalDBLetterboxStorage implements LetterboxStorage {
    // TODO: CLEAN UP AND FIGURE OUT HOW TO SEND RECIPIENT ID'S SEPARATE FROM THE MESSAGE

    protected Connection conn;

    public LocalDBLetterboxStorage() {

    }

    public void initObject(String dbFilePath) throws SQLException {
        this.conn = DriverManager.getConnection("jdbc:sqlite:" + dbFilePath);
        initDatabase();
    }

    private void initDatabase() throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS messages (" +
                    "message_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "recipient_id TEXT NOT NULL, " +
                    "body_blob BLOB NOT NULL, " +
                    "timestamp INTEGER NOT NULL" +
                    ");"); // create table
        }
    }

    @Override
    public void storeMessage(String userId, byte[] encryptedMessage) throws SQLException {
        String sql = "INSERT INTO messages (recipient_id, body_blob, timestamp) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) { // use sql
            ps.setString(1, userId);
            ps.setBytes(2, encryptedMessage); // db will store timestamp as when it reached mailbox
            ps.setLong(3, System.currentTimeMillis());
            ps.executeUpdate();
        }
    }

    @Override
    public List<byte[]> getMessages(String userId) throws SQLException {
        String sql = "SELECT body_blob FROM messages WHERE recipient_id = ?";
        List<byte[]> messages = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                byte[] content = rs.getBytes("body_blob");
                messages.add(content);
            }
        }
        return messages;
    }

    @Override
    public void deleteMessages(String userId) throws SQLException {
        String sql = "DELETE FROM messages WHERE recipient_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId); // Replace with actual ID or condition
            stmt.executeUpdate();
            // conn.close();
        }
    }

    public void vacuumDb() throws SQLException {
        Statement vacuum = conn.createStatement();
        vacuum.execute("VACUUM;");
        vacuum.close();
    }
}
