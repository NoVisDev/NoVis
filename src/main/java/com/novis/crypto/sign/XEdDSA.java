package com.novis.crypto.sign;

import com.novis.crypto.Crypto;
import com.novis.crypto.key.ECDHKeySuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XEdDSA {
    // sign using XEdDSA

    private static final Logger logger = LoggerFactory.getLogger(XEdDSA.class);

    public static byte[] generateSignature(ECDHKeySuite suite, byte[] message) {
        if (!suite.isExchanged()) { // require a ED25519 pair to sign
            logger.error("This suite has no ED25519 pair or sharedSecret, as it hasn't undergone " +
                        "key exchange. Aborting.");

            return null; // errors are messy in server code.
        }

        byte[] sig = new byte[64];

        Crypto.lazySodiumJava.cryptoSignDetached(sig,
                message,
                message.length,
                suite.getEd25519().getSecretKey().getAsBytes()
        );

        return sig;
    }

    public static byte[] generateSignatureSafe(ECDHKeySuite suite, byte[] message) {
        byte[] sig = XEdDSA.generateSignature(suite, message);

        // verify the signature

        if (sig != null) { // the function returns null if an error occurred, repeat this
            boolean isValid = Crypto.lazySodiumJava.cryptoSignVerifyDetached(
                    sig,
                    message,
                    message.length,
                    suite.getEd25519().getPublicKey().getAsBytes()
            );

            // throw exception
            if (!isValid) {
                logger.error("Signature not valid; check message and suite for logic errors.");

                return null;
            }

            return sig;
        } else {
            // error was carried forward from generateSignature, report this

            logger.error("generateSignature() failed, error was carried forward.");

            return null;
        }
    }

    public static boolean verifySignature(ECDHKeySuite suite, byte[] message, byte[] signature) {
        return Crypto.lazySodiumJava.cryptoSignVerifyDetached(
                signature,
                message,
                message.length,
                suite.getEd25519().getPublicKey().getAsBytes()
        );
    }
}
