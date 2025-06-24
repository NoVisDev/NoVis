package com.novis.crypto.hash;

import com.novis.crypto.Crypto;
import com.novis.crypto.key.ECDHKeySuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Blake2BFunction implements HashFunction {
    private static final Logger logger = LoggerFactory.getLogger(Blake2BFunction.class);

    @Override
    public byte[] hashBytes(byte[] input) {
        byte[] hash = new byte[Crypto.BLAKE2_MAX];

        // generate hash
        Crypto.lazySodiumJava.cryptoGenericHash(hash, Crypto.BLAKE2_MAX, input, input.length);

        return hash;
    }

    public byte[] hashBytes(byte[] input, int hashLength) {
        int _hashLength = hashLength;

        if (hashLength > 64 | hashLength < 1) {
            // validate input to ensure length is not below 1 and causes errors

            logger.error("BLAKE2B hash size below 1 or above 64: {}", hashLength);

            // set it to a reasonable length
            _hashLength = Crypto.BLAKE2_MAX; // most secure
        }

        byte[] hash = new byte[_hashLength];

        Crypto.lazySodiumJava.cryptoGenericHash(hash, _hashLength, input, input.length);

        return hash;
    }

    public byte[] hashBytes(byte[] input, ECDHKeySuite suite) { // signing with a ECDH key
        if (!suite.isExchanged()) { // require a ED25519 pair to sign
            logger.error("This suite has no ED25519 pair or sharedSecret, as it hasn't undergone " +
                    "key exchange. Aborting.");

            return null; // errors are messy in server code.
        }

        byte[] hash = new byte[Crypto.BLAKE2_MAX]; // max security
        byte[] key = suite.getSharedSecret();

        Crypto.lazySodiumJava.cryptoGenericHash(hash,
                Crypto.BLAKE2_MAX,
                input,
                input.length,
                key,
                key.length);

        return hash;
    }

    public byte[] hashBytes(byte[] input, byte[] rawKey) {
        byte[] hash = new byte[Crypto.BLAKE2_MAX]; // max security

        Crypto.lazySodiumJava.cryptoGenericHash(hash,
                Crypto.BLAKE2_MAX,
                input,
                input.length,
                rawKey,
                rawKey.length);

        return hash;
    }

    public byte[] hashBytes(byte[] input, byte[] rawKey, int hashLength) {
        int _hashLength = hashLength;

        if (hashLength > 64 | hashLength < 1) {
            // validate input to ensure length is not below 1 and causes errors

            logger.error("BLAKE2B hash size below 1 or above 64: {}", hashLength);

            // set it to a reasonable length
            _hashLength = Crypto.BLAKE2_MAX; // most secure
        }

        byte[] hash = new byte[_hashLength];

        Crypto.lazySodiumJava.cryptoGenericHash(hash, _hashLength, input, input.length,
                rawKey, rawKey.length);

        return hash;
    }

    public byte[] hashBytes(byte[] input, ECDHKeySuite suite, int hashLength) {
        if (!suite.isExchanged()) { // require a ED25519 pair to sign
            logger.error("This suite has no ED25519 pair or sharedSecret, as it hasn't undergone " +
                    "key exchange. Aborting.");

            return null; // errors are messy in server code.
        }

        int _hashLength = hashLength;

        if (hashLength > 64 | hashLength < 1) {
            // validate input to ensure length is not below 1 and causes errors

            logger.error("BLAKE2B hash size below 1 or above 64: {}", hashLength);

            // set it to a reasonable length
            _hashLength = Crypto.BLAKE2_MAX; // most secure
        }

        byte[] hash = new byte[_hashLength]; // max security
        byte[] key = suite.getSharedSecret();

        Crypto.lazySodiumJava.cryptoGenericHash(hash,
                _hashLength,
                input,
                input.length,
                key,
                key.length);

        return hash;
    }
}
