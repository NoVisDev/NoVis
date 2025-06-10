package com.novis.crypto;

import com.goterl.lazysodium.LazySodiumJava;
import com.goterl.lazysodium.SodiumJava;
import com.novis.common.utils.NativeLoader;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Security;

public class Crypto {
    public static final Logger logger = LoggerFactory.getLogger(Crypto.class);

    public static void init() {
        Security.addProvider(new BouncyCastleProvider());

        try {
            NativeLoader.loadLibsodiumFromResources(); // load first
            // then fire up LazySodium
            SodiumJava sodium = new SodiumJava();
            Crypto.lazySodiumJava = new LazySodiumJava(sodium);
        } catch (Exception e) {
            logger.error("error -> ", e);
        }
    }

    public static LazySodiumJava lazySodiumJava;
}
