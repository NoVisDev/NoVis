# Using the CRYPTO package

The crypto module is designed to be modular and able to be used by
contributors in their own forks and versions of NoVis.

## How to use:

Make sure you initiate the module (loading **libsodium.dll** and Bouncy Castle)
```java
public class Main {
    public static void main( /*args*/ ) {
        Crypto.init();
        // ...
    }
}
```

Make sure you load the right libsodium.dll file for your system. All will be provided in this repo.

## Rest of the module

### Ciphers used

The module only supports the cipher AES-GCM-SIV as of right now. Contributors
may be able to edit the module to add their own implementations or ciphers.

### ECDH in this module

The module again only supports Curve25519 ECDH keys and will implement Signal's
X3DH algorithm. Contributors may be able to edit the module to add their own
implementations or to support other curves.

### HKDF Subkey ID Format

| ID   | Context    | Use Case                                                      |
| ---- | ---------- | ------------------------------------------------------------- |
| `0`  | `Encrypt_` | Symmetric encryption key (AES/XChaCha) 🔐                     |
| `1`  | `Mac_____` | Message Authentication (MAC/HMAC) 🛡️                         |
| `2`  | `Sign____` | Ed25519 signing secret seed ✍️                                |
| `3`  | `IV______` | Nonce/IV derivation if needed 🌀                              |
| `4+` | `Backup__` | Any custom keys (e.g. for future proofing or key rotation) 🔄 |

#### Session specific

| ID  | Context    | Meaning                               |
| --- | ---------- | ------------------------------------- |
| `0` | `SessKey_` | Ephemeral session key                 |
| `1` | `SessMac_` | Ephemeral MAC for session             |
| `0` | `PersSign` | Persistent signing key                |
| `0` | `Root____` | Root encryption key (e.g. for vaults) |
