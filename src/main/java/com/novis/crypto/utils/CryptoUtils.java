package com.novis.crypto.utils;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;

public class CryptoUtils {
    private static final SecureRandom secureRandom = new SecureRandom();

    public static byte[] generateRandomNonce(int size) {
        byte[] nonce = new byte[size];
        secureRandom.nextBytes(nonce);
        return nonce;
    }

    public static SecretKey generateRandomAESKey(int keySize) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(keySize); // 128, 192, or 256 bits
        return keyGen.generateKey();
    }
}
