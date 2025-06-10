package com.novis;

import com.goterl.lazysodium.utils.KeyPair;
import com.novis.crypto.Crypto;
import com.novis.crypto.cipher.AESCipher;
import com.novis.crypto.key.ECDHGenerator;
import com.novis.crypto.utils.CryptoUtils;
import com.novis.crypto.utils.FormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.util.Objects;

public class EncryptionTest {
    public static final Logger logger = LoggerFactory.getLogger(EncryptionTest.class);

    public static void main(String[] args) throws Exception {
        Crypto.init();

        // generate two ECDH keypairs and generate a shared secret

        KeyPair me = ECDHGenerator.generateKeyPair();
        KeyPair other = ECDHGenerator.generateKeyPair();

        // generate shared secret

        assert me != null;
        assert other != null;
        byte[] sharedSecret = ECDHGenerator.generateSharedSecret(me, other);

        // the cipher object we will mutate to encrypt data using AES-GCM-SIV
        Cipher source = AESCipher.generateAGSCipherInstance();

        // random nonce
        byte[] nonce = CryptoUtils.generateRandomNonce(12);

        AESCipher.bindKeyAndSpecToCipher(source,
                sharedSecret,
                32,
                nonce,
                true); // set to encryption

        byte[] ciphertext = AESCipher.useCipherInstance(FormatUtils.utf8StringToBytes("How are you man i wanna eat"), source);
        // encrypt text

        AESCipher.bindKeyAndSpecToCipher(source,
                sharedSecret,
                32,
                nonce,
                false); // swap the decryption modes

        byte[] plaintext = AESCipher.useCipherInstance(ciphertext, source); // decrypt text

        String displayCiphertext = FormatUtils.bytesToUtf8String(ciphertext);
        String displayPlaintext = FormatUtils.bytesToUtf8String(plaintext);

        // display the two texts and compare to check if encryption worked.

        logger.info("Ciphertext: {}", displayCiphertext);
        logger.info("Plaintext: {}", displayPlaintext);

        logger.info("Equal? {}", Objects.equals(displayCiphertext, displayPlaintext));

        source = null; // Clear AES cipher instance to remove risks of side-channel attacks on residual keys and nonces
    }
}
