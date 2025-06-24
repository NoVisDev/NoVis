package com.novis.server.letterbox;

import com.novis.common.packet.data.KeyExchangePacketData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocalDBKeyStorage {

    protected Connection conn;

    public LocalDBKeyStorage() {

    }

    public void initObject(String dbFilePath) throws SQLException {
        this.conn = DriverManager.getConnection("jdbc:sqlite:" + dbFilePath);
        initDatabase();
    }

    private void initDatabase() throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(
                    "CREATE TABLE key_exchanges ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "sender TEXT NOT NULL,"
                    + "receiver TEXT NOT NULL,"
                    + "public_key BLOB NOT NULL,"
                    + "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ");"
            ); // create table
        }
    }

    public void storeExchange(String from, String to, byte[] public_key) throws SQLException {
        String sql = "INSERT INTO key_exchanges (sender, receiver, public_key) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) { // use sql
            ps.setString(1, from);
            ps.setString(2, to); // db will store timestamp as when it reached mailbox
            ps.setBytes(3, public_key);
            ps.executeUpdate();
        }
    }

    public KeyExchangePacketData getExchanges(String userId, String sender) throws SQLException {
        String sql = "SELECT public_key FROM messages WHERE receiver = ? AND sender = ?";
        KeyExchangePacketData kep;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userId);
            ps.setString(2, sender);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                byte[] content = rs.getBytes("public_key");
                kep = new KeyExchangePacketData(content, userId, sender);

                return kep;
            }
        }

        return null;
    }

    public void deleteMessages(String userId, String sender) throws SQLException {
        String sql = "DELETE FROM messages WHERE receiver = ? AND sender = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId); // Replace with actual ID or condition
            stmt.setString(2, sender);
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
