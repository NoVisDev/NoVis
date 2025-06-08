# 🛡️ SECURITY.md

## Overview

NoVis is a privacy-first communication platform designed to offer end-to-end encryption, decentralised trust, and metadata resistance. Our mission is simple: secure communications, no surveillance. This document outlines the key principles, cryptographic implementations, and the threat models we protect against.

---

## 🔐 Cryptography

### Key Exchange

- Utilizes **X3DH** (Extended Triple Diffie-Hellman) for initial key exchange and forward secrecy.
- Ephemeral identifiers are used to store public keys, decoupling real identity from cryptographic presence.
- Optional integration with **ECDH** or **RSA** as fallback.

### Message Integrity

- All messages are signed and validated with **HMAC-SHA256**.
- Nonces and message tokens are used to prevent replay attacks and verify end-to-end authenticity.

### Encryption

- Messages are encrypted using industry-standard algorithms (e.g., **AES-256-GCM**).
- Custom/proprietary algorithms may be layered on top **only for obfuscation**, never as a core cryptographic primitive.

> 🔒 Core cryptographic components follow audited and peer-reviewed standards.

---

## 🧠 Trust Model

### Decentralized Trust Verification

- Users and their public keys are linked to **ephemeral identifiers** — never to real-world identity.
- Trust nodes act as **verifiers**. Clients can verify public keys via:
  - Known contacts (web-of-trust model)
  - Multiple trust nodes
  - Central, auditable fallback nodes

### Client Validation Process

When initiating communication:
1. Retrieve recipient’s public key via their ephemeral ID.
2. Verify key against multiple trusted nodes or contacts.
3. If trust threshold is met (e.g., 3 trust nodes, 2 known contacts), proceed to secure handshake (X3DH).
4. Begin encrypted session.

> ❗ Servers are not trusted for verification. All trust is validated client-side or through trusted third parties.

---

## 📬 Message Handling

- Sender identity is encrypted within the message; only ephemeral ID is in plaintext.
- Timestamps are included, along with nonces and HMACs, for integrity and ordering.
- Messages include a “round-trip proof” (challenge–response) that must be decrypted and returned by recipient.

> Prevents server from modifying or spoofing messages (i.e., **MitM/MKS attacks**).

---

## 📦 Dropboxes & Letterboxes

- After initial trust is established, clients may create **temporary encrypted mailboxes** (dropboxes) to exchange messages.
- Server stores only an **anonymised sender key**.
- Once a message is retrieved, it is immediately deleted.
- Server implementation must be **open source and hash-verifiable** against central repo builds.

---

## 🧼 Data Deletion & Ephemeral Presence

Users may initiate two types of disconnection:

### 1. **Partial Disconnect**
- Temporarily logs off user.
- Ephemeral ID is removed from active memory.
- Locked messages remain encrypted with user’s key and may be retrieved on re-login.

### 2. **Full Deletion**
- Completely removes user’s ephemeral ID and stored data.
- Trust nodes are queried to confirm user presence returns null.
- Archived messages tied to user are permanently deleted or sealed with user’s key.

---

## 👻 Metadata Resistance

- Use of **dummy traffic** and padding to defeat traffic analysis and social graphing.
- TOR support available for optional routing anonymity.
- Encrypted sender fields and forward secrecy ensure minimal traceability.

---

## ⚠️ Threat Model Protection

| Threat | Mitigated? | Notes |
|--------|------------|-------|
| 🧍 Man-in-the-middle | ✅ | X3DH + key verification via trust model |
| 🧠 Malicious server (MKS) | ✅ | Round-trip verification & HMAC |
| 🐾 Metadata leaks | ✅ | Sender encryption + dummy traffic |
| 🔁 Replay attacks | ✅ | Nonces + HMAC |
| 🔍 Traffic analysis | ✅ | Optional TOR + dummy messages |
| 🪪 Identity leaks | ✅ | Ephemeral ID-based abstraction |

---

## 🧪 Future Considerations

- Zero-knowledge proofs for message deletion and trust validation
- Optional social graph obfuscation techniques
- Integration with self-hosted DID systems
- Federated trust node reputation scoring

---

## 🔓 Open Source & Auditability

- All server builds are open source and include reproducible hashes.
- Verified and community-trusted builds will be listed in the official [trusted-mods registry].
- Proprietary components used **only for obfuscation**, not encryption or validation.

> Community is encouraged to audit, contribute, and suggest improvements.

---

## ✉️ Contact

For vulnerabilities, concerns, or suggestions, please open an issue or reach out via our secure channels (coming soon).

---

_NoVis: No surveillance. Just comms._