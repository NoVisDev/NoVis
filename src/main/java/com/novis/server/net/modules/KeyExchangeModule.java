package com.novis.server.net.modules;

import com.novis.common.packet.data.KeyExchangePacketData;
import com.novis.common.packet.data.PacketData;
import com.novis.server.letterbox.LocalDBKeyStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class KeyExchangeModule {
    private static final LocalDBKeyStorage ldbks = new LocalDBKeyStorage();
    private static final Logger logger = LoggerFactory.getLogger(KeyExchangeModule.class);

    public KeyExchangeModule() {
        try {
            ldbks.initObject("data/kem.db");
        } catch (Exception e) {
            logger.error("error -> ", e);
        }
    }

    public void dropOffExchange(PacketData data) {
        KeyExchangePacketData kepd = (KeyExchangePacketData) data;

        try {
            ldbks.storeExchange(kepd.getFrom(), kepd.getToExchangeWith(), kepd.getPublicKey());
        } catch (SQLException e) {
            logger.error("error -> ", e);
        }
    }
}
