package com.novis.crypto.hash;

import com.novis.crypto.Crypto;

import java.util.Arrays;

public class HMACUtil {
    // utilize BLAKE2B to create a HMAC

    public byte[] hmacBlake2b(byte[] msg, byte[] key) {
        byte[] out = new byte[Crypto.HMAC_SIZE]; // or 64, depending on app
        Crypto.lazySodiumJava.cryptoAuth(out, msg, msg.length, key);
        return out;
    }

    public boolean verifyHmac(byte[] msg, byte[] key, byte[] expectedMac) {
        byte[] mac = hmacBlake2b(msg, key);
        return Arrays.equals(mac, expectedMac);
    }
}
