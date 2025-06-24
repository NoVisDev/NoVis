package com.novis.crypto.hash;

import com.goterl.lazysodium.interfaces.KeyDerivation;
import com.novis.crypto.Crypto;
import com.novis.crypto.utils.FormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class HKDFUtil {
    private static final Logger logger = LoggerFactory.getLogger(HKDFUtil.class);

    /**
     * Derive a subkey from a master key using libsodium KDF
     *
     * @param masterKey 32-byte master key (crypto_kdf_KEYBYTES)
     * @param context   Exactly 8 ASCII chars identifying key purpose
     * @param subkeyId  Identifier for the derived key
     * @param subkeyLen Length of derived key (max 64 bytes)
     * @return Derived subkey bytes
     */
    public static byte[] deriveSubkey(byte[] masterKey, String context, long subkeyId, int subkeyLen) {
        if (masterKey == null || masterKey.length != KeyDerivation.MASTER_KEY_BYTES) {
            logger.error(
                    "error -> ",
                    new IllegalArgumentException("Master key must be exactly " + KeyDerivation.MASTER_KEY_BYTES + " bytes")
            );
        }
        if (context == null || context.length() != 8) {
            logger.error(
                    "error -> ",
                    new IllegalArgumentException("Context must be exactly 8 characters")
            );
        }
        if (subkeyLen <= 0 || subkeyLen > 64) {
            logger.error(
                    "error -> ",
                    new IllegalArgumentException("Subkey length must be between 1 and 64 bytes")
            );
        }

        byte[] subkey = new byte[subkeyLen];
        int success = Crypto.lazySodiumJava.cryptoKdfDeriveFromKey(
                subkey,
                subkeyLen,
                subkeyId,
                FormatUtils.utf8StringToBytes(context),
                masterKey
        );

        if (success < 0) {
            logger.error("error -> ", new RuntimeException("Failed to derive subkey"));
        }

        return subkey;
    }

    /**
     * Generate a new random master key (32 bytes)
     *
     * @return master key bytes
     */
    public static byte[] generateMasterKey() {
        byte[] masterKey = new byte[KeyDerivation.MASTER_KEY_BYTES];
        Crypto.lazySodiumJava.cryptoKdfKeygen(masterKey);
        return masterKey;
    }
}
