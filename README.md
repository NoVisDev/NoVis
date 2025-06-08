# 🕶️ noVis – Privacy Ain’t Dead, It’s Just Encrypted

noVis is a lightweight, E2E-encrypted comms framework built for privacy purists, code nerds, and digital ghosts. Designed with hardened crypto pathways and optional integrations into platforms like Discord, Telegram, and TOR – this ain’t your average secure messenger.

## 🔐 Crypto Stack – Multiple Layers of Paranoia

We don’t trust, we verify. So we’re packing the full cryptographic buffet:

- **ECDH (Elliptic Curve Diffie-Hellman)** – For that sweet, sweet forward secrecy. Ephemeral key exchange like a ghost handshake.
- **RSA** – Optional asymmetric encryption layer for hybrid flows or legacy support.
- **AES (Advanced Encryption Standard)** – 256-bit symmetric encryption. Fast, brutal, effective.
- **HMAC (Hash-based Message Authentication Code)** – Verifies data integrity, keeps tampering clowns out.

## ✅ Peer Verification – Trust, But Only Once

New contacts go through a **mutual verification dance**. Either via QR code exchange, voice fingerprint, or out-of-band passphrase. Once you verify, all comms get locked down tighter than Fort Knox with no backdoors.

## 📬 Letterbox – Dead Drop Server Functionality

Introducing **Letterbox**, a stealthy dead-drop server for offline message passing. Think of it as your personal chalk mark under the park bench. Messages are encrypted end-to-end and self-destruct after pickup. Clean. Anonymous. No metadata trails.

## 🌐 Plug & Ghost – Integration Ready

Want to blend into the crowd? noVis plays nice with:

- **Discord Bots** – Pipe encrypted payloads through bot DMs.
- **Telegram** – Use private bots as encrypted bridges.
- **Custom Hooks** – Roll your own plugins or hook into other platforms via adapters.

## 🧅 TOR Support – Move Like a Shadow

Need to vanish? Route all traffic over **TOR**. Whether you're running a hidden service or just want your traffic to go walkabout – noVis can slip through the net like vapor.

## 🛠️ Roadmap

- [x] Core E2E stack (AES, RSA, ECDH, HMAC)
- [x] Peer verification
- [x] Letterbox dead-drop
- [ ] Full Discord integration
- [ ] Telegram bot bridge
- [ ] Desktop client w/ TOR routing baked in
- [ ] P2P mesh prototype

## 🧠 Disclaimer

This is for **educational use** or **legit privacy ops** only. Don’t be dumb. If you’re using noVis for illegal activity, not only are you violating the law – you’re probably not as slick as you think you are. Stay safe, stay smart.

🧩 Want to contribute? Please read our [Contributing Guidelines](./CONTRIBUTING.md)


📝 Note: NoVis is in early development. The current license (MIT) is chosen to encourage contribution and flexibility. In the future, we may introduce additional licensing for server deployments to ensure transparency and user trust — contributors will be notified and included in that process.

---

Built with paranoia, by privacy freaks. 🫡
