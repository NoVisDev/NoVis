# Proposed Feature: Key Verification System

## Overview

This system allows clients to independently verify the identity and key of another client without relying solely on the server’s honesty. The goal is to prevent impersonation, downgrade attacks, and server-based MITM attempts by shifting trust from the server to a web of peer and node verification.

## Design Goals

- 🔒 Remove server from the trust loop
- 🧠 Use existing social or node-based webs to verify identities
- ⚠️ Detect unknown or unverified keys and warn users
- 📤 Keep metadata exposure minimal during verification

## Flow Summary

1. **Initial Key Lookup**  
   - Client wants to send a message to another user.
   - Queries the server for the recipient's **ephemeral identifier** and **public key info**.

2. **Trust Check via Contacts**  
   - If client already communicated with this ID, it checks cached keys.
   - If not, it queries **mutual contacts** or **trusted nodes** for key verification:
     - “Do you know this key?”
     - “When was the last time you verified it?”

3. **Trust Score or Threshold**  
   - A minimum number of confirmations from trusted sources is needed before accepting the key.
   - This trust quorum avoids reliance on a single source.

4. **Initiating DH Session**  
   - Once key is confirmed, client proceeds with X3DH or custom DH initiation.
   - HMAC or MAC-based challenge-response ensures recipient identity during key agreement.

5. **Caching and Re-checking**  
   - Keys are cached but re-verified periodically.
   - Any mismatch (key change, missing contact trust) triggers a warning or reconfirmation.

## Trust Considerations

- 🔁 **Rotating Keys**  
  - If keys rotate, previous trusted nodes must reconfirm. Or notify users clearly.

- 🎭 **Impersonation Prevention**  
  - Without enough verifications, warn user that contact may be spoofed or fake.

- 🧱 **Server Spoofed Keys**  
  - If server injects malicious keys, they’ll fail trust verification from peers/nodes.

## Dev Notes

🛠️ **Useful Tools & Libs**:

- **Cryptographic Verification**:
  - `Bouncy Castle` or `Java Crypto API` for RSA/ECDSA sigs and HMAC
  - `libsodium` (if porting to native or C/C++ layer)

- **Key Caching**:
  - `SQLite`, `Realm`, or file-based storage for storing known keys & trust levels

- **Key Verification Networking**:
  - Use `OkHttp` or `Netty` to query other nodes/contacts for public key confirmation
  - JSON exchange via `Jackson` or `Gson`

- **Trust Graph Handling** (Optional):
  - Graph-based trust weights (optional advanced feature)
  - Can be visualized with `GraphStream` or sent as JSON trees

