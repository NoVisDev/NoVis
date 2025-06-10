package com.novis.crypto.key;

import com.goterl.lazysodium.utils.KeyPair;
import com.novis.crypto.Crypto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ECDHGenerator {
    public static final Logger logger = LoggerFactory.getLogger(ECDHGenerator.class);

    public static KeyPair generateKeyPair() {
        try {
            return Crypto.lazySodiumJava.cryptoBoxKeypair();
        } catch (Exception e) {
            logger.error("error -> ", e);
        }

        return null;
    }

    public static byte[] generateSharedSecret(KeyPair mine, KeyPair other) {
        // session keys here, returned as shared secret
        byte[] sharedSecret = new byte[32];

        boolean success = Crypto.lazySodiumJava.cryptoBoxBeforeNm(
                sharedSecret,
                mine.getSecretKey().getAsBytes(),
                other.getPublicKey().getAsBytes()
        );

        return sharedSecret;
    }
}
