package com.novis.crypto;

import com.goterl.lazysodium.LazySodiumJava;
import com.goterl.lazysodium.SodiumJava;
import com.goterl.lazysodium.interfaces.PwHash;
import com.novis.common.utils.NativeLoader;
import com.sun.jna.NativeLong;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Security;

public class Crypto {
    public static final Logger logger = LoggerFactory.getLogger(Crypto.class);

    // Hashing constants

    public static final int SHA256_BYTES = 32;
    public static final int SHA512_BYTES = 64;
    public static final int BLAKE2_MAX = 64;
    public static final int BLAKE2_32 = 32;
    public static final int ARGON2_MAX = 64;
    public static final int ARGON2_32 = 32;
    public static final int ARGON2_SALT_SIZE = 16;

    public static final int HMAC_SIZE = 64;

    public static final class PwhashParams {

        // Algorithm: Argon2id (libsodium default)
        public static final PwHash.Alg ALG_ARGON2ID13 = PwHash.Alg.PWHASH_ALG_ARGON2ID13;

        // ===== INTERACTIVE =====
        // Recommended for user login, fast but safe
        public static final int OPSLIMIT_INTERACTIVE = 2;
        public static final NativeLong MEMLIMIT_INTERACTIVE = new NativeLong(64L * 1024 * 1024); // 64MB

        // ===== MODERATE =====
        // Good for secure apps or keygen w/ balanced tradeoff
        public static final int OPSLIMIT_MODERATE = 3;
        public static final NativeLong MEMLIMIT_MODERATE = new NativeLong(256L * 1024 * 1024); // 256MB

        // ===== SENSITIVE =====
        // Maximum security (wallets, vaults, long-term keys)
        public static final int OPSLIMIT_SENSITIVE = 4;
        public static final NativeLong MEMLIMIT_SENSITIVE = new NativeLong(1024L * 1024 * 1024); // 1GB

        private PwhashParams() {
            // Prevent instantiation
        }
    }


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
