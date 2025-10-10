---

## 🧩 **Core Cryptography & Protocols**

*(Builds the foundation for encryption, handshakes, key exchange, etc.)*

* **Noise Protocol Framework (Trevor Perrin)** — already listed, crucial for designing modular handshakes.
* **MLS (Messaging Layer Security, IETF)** — already listed, defines modern group messaging security.
* **NIST AES / SHA specs** — already listed, for symmetric encryption and hashing.

**Add:**

* 🔹 **TLS 1.3 RFC 8446** — study for authenticated encryption, session resumption, and PSKs.
* 🔹 **Signal Protocol whitepaper (Marlinspike & Perrin)** — for Double Ratchet, X3DH, and session recovery.
* 🔹 **X25519 & Ed25519 papers (Curve25519: Bernstein)** — understand key exchange & signature schemes.
* 🔹 **HKDF (RFC 5869)** — used in Noise, Signal, and TLS for deriving secure keys from entropy pools.

---

## 🌐 **P2P & Distributed Communication**

*(Focus on inter-node messaging, routing, and privacy over decentralized systems)*

* 🔹 **libp2p Specification** — used in IPFS and many decentralized systems; covers peer discovery, transport, NAT traversal, and pubsub.
* 🔹 **Tor Design Paper (Dingledine, Mathewson, Syverson)** — for onion routing and circuit-based privacy.
* 🔹 **Mixminion / Loopix Papers** — for understanding mixnets and timing obfuscation in message relay.
* 🔹 **WebRTC DataChannel Spec** — real-world, NAT-bypassing, encrypted P2P transport.
* 🔹 **Matrix Protocol Spec** — for decentralized messaging over federated networks (bridging model).

---

## 🧠 **System Architecture & Threat Modeling**

*(How to think about privacy, metadata resistance, and adversarial modeling)*

* 🔹 **NIST SP 800-63B** — digital identity, session management, and authentication strength.
* 🔹 **Privacy Threat Modeling by LINDDUN** — methodology for privacy risks (linkability, identifiability, etc.).
* 🔹 **Signal’s Sealed Sender and Contact Discovery Papers** — practical metadata protection.
* 🔹 **Apple’s Private Relay whitepaper** — multi-hop relay privacy for IP masking.
* 🔹 **I2P Technical Overview** — another approach to anonymizing routing, different from Tor.

---

## 🕵️‍♂️ **Privacy UX & User Trust**

*(What users *want* from privacy apps and how they perceive trustworthiness)*

* 🔹 **EFF “Secure Messaging Scorecard”** — user-focused privacy expectations (encryption, metadata, auditability).
* 🔹 **Mozilla Privacy UX Guidelines** — designing transparency and informed consent.
* 🔹 **“The Human Factor in End-to-End Encryption” (ACM 2022)** — how non-technical users understand security guarantees.
* 🔹 **“Privacy and Usability of Secure Messaging Apps” (2020, IEEE)** — user expectations vs real-world app behavior.

---

## 🔒 **Advanced / Optional Topics**

*(For deep dives or experimental system ideas)*

* 🔹 **Pond by Adam Langley** — asynchronous, metadata-resistant messaging design.
* 🔹 **Cwtch Project Docs** — builds on Tor + Ricochet for metadata resistance and group privacy.
* 🔹 **Deniable Authentication & Forward Secrecy Papers** — deep cryptographic concepts relevant to user-facing privacy.
* 🔹 **Zero Knowledge Proof basics (zk-SNARKs)** — if you ever want privacy-preserving identity verification.
* 🔹 **Multi-path & Multi-device synchronization (Session / MLS Extensions)** — for secure multi-device UX.

---

## 🧭 Suggested Reading Flow

**Phase 1 – Core encryption:** Noise → Signal → TLS 1.3 → X25519
**Phase 2 – Group security:** MLS → HKDF → NIST SP 800-63B
**Phase 3 – Networking & privacy:** libp2p → Tor → Mixnets → Matrix
**Phase 4 – User privacy & UX:** EFF Scorecard → Privacy UX Guidelines
**Phase 5 – Advanced systems:** Cwtch → ZKPs → Deniability

---

Would you like me to make a **visual map (in Excalidraw)** showing how these papers connect (e.g., "Noise → Signal → MLS" or "Tor → Mixnet → P2P privacy")?
It’ll help you plan the NoVis architecture reading order and system layout visually.
