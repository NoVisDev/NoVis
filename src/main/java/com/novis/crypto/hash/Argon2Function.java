package com.novis.crypto.hash;

import com.goterl.lazysodium.interfaces.PwHash;
import com.novis.crypto.Crypto;
import com.sun.jna.NativeLong;

public class Argon2Function implements HashFunction {
    // TODO: add other implementations; length of hash and params, length of salt and params, and lone

    /**
     * Simply hash an input with a random salt and INTERACTIVE level security
     *
     * @param input raw byte data to hash
     * @return bytes of the hash only
     */
    @Override
    public byte[] hashBytes(byte[] input) {
        byte[] salt = Crypto.lazySodiumJava.randomBytesBuf(Crypto.ARGON2_SALT_SIZE);
        byte[] hash = new byte[Crypto.ARGON2_MAX];

        Crypto.lazySodiumJava.cryptoPwHash(
                hash,
                hash.length,
                input,
                input.length,
                salt,
                Crypto.PwhashParams.OPSLIMIT_INTERACTIVE,
                Crypto.PwhashParams.MEMLIMIT_INTERACTIVE,
                Crypto.PwhashParams.ALG_ARGON2ID13
        );

        return hash;
    }

    /**
     * NOTE: ii functions return two arguments, a hash and a salt for ARGON2ID
     *
     *
     * Hash an input with a random salt and INTERACTIVE level security
     *
     * @param input raw byte data to hash
     * @return bytes of the hash at index 0, bytes of salt at index 1
     */
    public byte[][] hashBytesii(byte[] input) {
        byte[] salt = Crypto.lazySodiumJava.randomBytesBuf(Crypto.ARGON2_SALT_SIZE);
        byte[] hash = new byte[Crypto.ARGON2_MAX];

        Crypto.lazySodiumJava.cryptoPwHash(
                hash,
                hash.length,
                input,
                input.length,
                salt,
                Crypto.PwhashParams.OPSLIMIT_INTERACTIVE,
                Crypto.PwhashParams.MEMLIMIT_INTERACTIVE,
                Crypto.PwhashParams.ALG_ARGON2ID13
        );

        return new byte[][] {hash, salt};
    }

    /**
     * NOTE: ii functions return two arguments, a hash and a salt for ARGON2ID
     *
     *
     * Hash an input with a random salt and customizable levels of security
     *
     * @param input raw byte data to hash
     * @param opsLimit the argument for argon2id's operations limit
     * @param memLimit the argument for argon2id's memory limit
     * @param alg the argument for argon2's chosen algorithm. do not change from default, risks attacks.
     * @return bytes of the hash at index 0, bytes of salt at index 1
     */
    public byte[][] hashBytesii(byte[] input, int opsLimit, NativeLong memLimit, PwHash.Alg alg) {
        byte[] salt = Crypto.lazySodiumJava.randomBytesBuf(Crypto.ARGON2_SALT_SIZE);
        byte[] hash = new byte[Crypto.ARGON2_MAX];

        Crypto.lazySodiumJava.cryptoPwHash(
                hash,
                hash.length,
                input,
                input.length,
                salt,
                opsLimit,
                memLimit,
                alg
        );

        return new byte[][] {hash, salt};
    }

    /**
     * NOTE: ii functions return two arguments, a hash and a salt for ARGON2ID
     *
     *
     * Hash an input with a custom salt
     *
     * @param input raw byte data to hash
     * @param salt raw byte data of the salt
     * @return bytes of the hash at index 0, bytes of salt at index 1
     */
    public byte[][] hashBytesii(byte[] input, byte[] salt) {
        byte[] hash = new byte[Crypto.ARGON2_MAX];

        Crypto.lazySodiumJava.cryptoPwHash(
                hash,
                hash.length,
                input,
                input.length,
                salt,
                Crypto.PwhashParams.OPSLIMIT_INTERACTIVE,
                Crypto.PwhashParams.MEMLIMIT_INTERACTIVE,
                Crypto.PwhashParams.ALG_ARGON2ID13
        );

        return new byte[][] {hash, salt};
    }
}
