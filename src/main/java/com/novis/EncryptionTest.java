package com.novis;

import com.goterl.lazysodium.utils.KeyPair;
import com.novis.crypto.Crypto;
import com.novis.crypto.cipher.AESCipher;
import com.novis.crypto.hash.Argon2Function;
import com.novis.crypto.hash.Blake2BFunction;
import com.novis.crypto.key.ECDHGenerator;
import com.novis.crypto.key.ECDHKeySuite;
import com.novis.crypto.sign.XEdDSA;
import com.novis.crypto.utils.CryptoUtils;
import com.novis.crypto.utils.FormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.Objects;

public class EncryptionTest {
    public static final Logger logger = LoggerFactory.getLogger(EncryptionTest.class);

    public static void blake2b(ECDHKeySuite suite) {
        Blake2BFunction b2b = new Blake2BFunction();

        byte[] example = FormatUtils.utf8StringToBytes("hell");

        logger.info("BLAKE2B-512 Hash of 'hello': {}", FormatUtils.bytesToHex(
                b2b.hashBytes(
                        example
                )
        ));

        logger.info("BLAKE2B-256 Hash of 'hello': {}", FormatUtils.bytesToHex(
                b2b.hashBytes(example, Crypto.BLAKE2_32)
        ));

        logger.info("BLAKE2B(8 bit) Hash of 'hello': {}", FormatUtils.bytesToHex(
                b2b.hashBytes(example, 1)
        ));

        logger.info("BLAKE2B(erroneous, 512bit) Hash of 'hello': {}", FormatUtils.bytesToHex(
                b2b.hashBytes(example, -9)
        ));
    }

    public static void argon2id() {
        Argon2Function a2f = new Argon2Function();

        byte[][] hash = a2f.hashBytesii("hello".getBytes(StandardCharsets.UTF_8));
        byte[][] hash2 = a2f.hashBytesii(
                "hello".getBytes(StandardCharsets.UTF_8),
                Crypto.PwhashParams.OPSLIMIT_SENSITIVE,
                Crypto.PwhashParams.MEMLIMIT_SENSITIVE,
                Crypto.PwhashParams.ALG_ARGON2ID13
        );

        logger.info("ARGON2ID hash of 'hello' at INTERACTIVE level: {}", FormatUtils.bytesToHex(
                hash[0]
        ));

        logger.info("ARGON2ID hash of 'hello' at SENSITIVE level: {}", FormatUtils.bytesToHex(
                hash2[0]
        ));
    }

    public static void main(String[] args) throws Exception {
        Crypto.init();

        // generate two ECDH keypairs and generate a shared secret

        ECDHKeySuite me = ECDHGenerator.generateKeyPair();
        ECDHKeySuite other = ECDHGenerator.generateKeyPair();

        // generate shared secret

        assert me != null;
        assert other != null;

        ECDHKeySuite.update(me, other.getX25519());
        byte[] sharedSecret = me.getSharedSecret();

        /*
        // the cipher object we will mutate to encrypt data using AES-GCM-SIV
        Cipher source = AESCipher.generateAGSCipherInstance();

        // random nonce
        byte[] nonce = CryptoUtils.generateRandomNonce(12);

        AESCipher.bindKeyAndSpecToCipher(source,
                sharedSecret,
                32,
                nonce,
                true); // set to encryption

        String initialPlaintext = "How are you man i wanna eat";
        byte[] ciphertext = AESCipher.useCipherInstance(FormatUtils.utf8StringToBytes(initialPlaintext), source);
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

        logger.info("Equal? {}", Objects.equals(initialPlaintext, displayPlaintext));

        // test signatures

        byte[] signature = XEdDSA.generateSignatureSafe(me, ciphertext);

        if (signature == null) {
            // signing failed

            logger.error("generateSignature carried forward error");
        } else {
            // verify signature

            logger.info("Signature is |{}| and is |{}| ",
                    FormatUtils.bytesToUtf8String(signature),
                    XEdDSA.verifySignature(me, ciphertext, signature));
        }
        */

        // hashing test

        blake2b(me);
        argon2id();

        // source = null; // Clear AES cipher instance to remove risks of side-channel attacks on residual keys and nonce
    }
}
