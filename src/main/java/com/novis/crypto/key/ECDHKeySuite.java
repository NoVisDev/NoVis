package com.novis.crypto.key;

import com.goterl.lazysodium.utils.Key;
import com.goterl.lazysodium.utils.KeyPair;

public class ECDHKeySuite {
    // Contains both ECDH X25519 keys and its matching Ed25519 signing key

    private KeyPair x25519, ed25519; // x25519 and ed25519
    // x25519 set first as we cannot derive ed25519 without a shared secret

    private byte[] sharedSecret;
    // store the shared secret in byte form

    private boolean exchanged = false;
    // hold the status of the key exchange for signatures

    private ECDHKeySuite(KeyPair x25519) {
        this.x25519 = x25519;

        // make sure initialization and related functions are private
    }

    private void generateOtherKeys(KeyPair otherX25519Pair) {
        // generate other keys from another x25519 pair, generating a shared secret and ED25519

        this.sharedSecret = ECDHGenerator.generateSharedSecret(
                this.x25519,
                otherX25519Pair
        ); // generate shared secret

        this.ed25519 = ECDHGenerator.toEd25519FromSharedSecret(this.sharedSecret);
        // generate ed25519 pair

        exchanged = true;
    }

    public byte[] getSharedSecret() {
        return sharedSecret;
    }

    public KeyPair getEd25519() {
        return ed25519;
    }

    public KeyPair getX25519() {
        return x25519;
    }

    public boolean isExchanged() {
        return exchanged;
    }

    // generator function
    public static ECDHKeySuite getInstance(KeyPair mine, KeyPair other) {
        // generate knowing both key pairs

        ECDHKeySuite keySuite = new ECDHKeySuite(mine);

        keySuite.generateOtherKeys(other);

        return keySuite;
    }

    // generator function 2
    public static ECDHKeySuite getInstance(KeyPair mine) {
        // generate not knowing both keypairs. (unexchanged)

        return new ECDHKeySuite(mine);
    }

    // update a key that is unexchanged
    public static ECDHKeySuite update(ECDHKeySuite suite, KeyPair other) {
        // update a key suite that is un exchanged

        suite.generateOtherKeys(other);

        return suite;
    }
}
