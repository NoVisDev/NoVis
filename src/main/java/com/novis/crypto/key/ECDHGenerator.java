package com.novis.crypto.key;

import com.goterl.lazysodium.utils.KeyPair;
import com.novis.crypto.Crypto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ECDHGenerator {
    public static final Logger logger = LoggerFactory.getLogger(ECDHGenerator.class);

    public static ECDHKeySuite generateKeyPair() {
        try {
            return ECDHKeySuite.getInstance(Crypto.lazySodiumJava.cryptoBoxKeypair());
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

        if(!success)  {
            logger.error("Could not generate a shared secret.");
        }

        return sharedSecret;
    }

    public static KeyPair toEd25519FromSharedSecret(byte[] sharedSecret) {
        // create signing keys (X25519 to ED25519)

        try {
            byte[] seed = new byte[32];
            Crypto.lazySodiumJava.cryptoGenericHash(seed, 32, sharedSecret, sharedSecret.length, null, 0);

            return Crypto.lazySodiumJava.cryptoSignSeedKeypair(seed);
        } catch (Exception e) {
            logger.error("error -> ", e);
        }

        return null;
    }
}
