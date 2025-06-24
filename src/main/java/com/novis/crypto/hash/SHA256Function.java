package com.novis.crypto.hash;

import com.novis.crypto.Crypto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SHA256Function implements HashFunction {
    private static final Logger logger = LoggerFactory.getLogger(SHA256Function.class);

    @Override
    public byte[] hashBytes(byte[] input) {
        byte[] hash = new byte[Crypto.SHA256_BYTES];

        // generate hash
        Crypto.lazySodiumJava.cryptoHashSha256(hash, input, input.length);

        return hash;
    }
}
