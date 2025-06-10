package com.novis.crypto.cipher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class AESCipher {
    // run aes-gcm-siv

    public static final Logger logger = LoggerFactory.getLogger(AESCipher.class);

    public static Cipher generateAGSCipherInstance() {
        try {
            return Cipher.getInstance("AES/GCM-SIV/NoPadding", "BC");
        } catch (Exception e) {
            logger.error("error -> ", e);
        }

        return null;
    }

    public static void bindKeyAndSpecToCipher(Cipher source, byte[] sharedSecret, int size, byte[] nonce, boolean toEncrypt) {
        // generate key and spec and bind to a Cipher, for AES-GCM-SIV

        if (!(sharedSecret.length == size | nonce.length == 12)) {
            logger.error("Key bytes length or nonce are in a incorrect format: key length {}, nonce size {}",
                    sharedSecret.length,
                    nonce.length);
        }

        SecretKeySpec key = new SecretKeySpec(sharedSecret, 0, size, "AES");
        GCMParameterSpec spec = new GCMParameterSpec(128, nonce);

        try {
            source.init(toEncrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, key, spec);
        } catch (Exception e) {
            logger.error("error -> ", e);
        }
    }

    public static void bindKeyAndSpecToCipher(Cipher source, byte[] keyBytes, byte[] nonce, boolean toEncrypt) {
        // generate key and spec and bind to a Cipher, for AES-GCM-SIV

        if (!(keyBytes.length == 32 | nonce.length == 12)) {
            logger.error("Key bytes length or nonce are in a incorrect format: key length {}, nonce size {}",
                    keyBytes.length,
                    nonce.length);
        }

        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        GCMParameterSpec spec = new GCMParameterSpec(128, nonce);

        try {
            source.init(toEncrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, key, spec);
        } catch (Exception e) {
            logger.error("error -> ", e);
        }
    }

    public static byte[] useCipherInstance(byte[] input, Cipher cipher) {
        // assuming cipher instance is already initialized using bindKeyAndSpecToCipher

        try {
            return cipher.doFinal(input);
        } catch (Exception e) {
            logger.error("error -> ", e);
        }

        return null;
    }
}
