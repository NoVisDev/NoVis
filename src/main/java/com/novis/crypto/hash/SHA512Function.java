package com.novis.crypto.hash;

import com.novis.crypto.Crypto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SHA512Function implements HashFunction {
    private static final Logger logger = LoggerFactory.getLogger(SHA512Function.class);

    @Override
    public byte[] hashBytes(byte[] input) {
        byte[] hash = new byte[Crypto.SHA512_BYTES];

        // generate hash
        Crypto.lazySodiumJava.cryptoHashSha512(hash, input, input.length);

        return hash;
    }
}