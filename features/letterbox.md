# Feature: Letterbox

## Overview
Dead-drop servers for dummies (the non-cybersecurity-specialist public)

## Design Goals
- Hold messages (encrypted)
- Hide IPs, by allowing messages to only be fetchable by clients requesting it from servers -- meaning they can utilize TOR.
- Hide sender IP from the recipient IP.

## Flow Summary
- Clients send messages to a letterbox hosted on a NoVis server.
- Server verify the client's signature with a public key on their database (tied to a user's ephemeral ID)
- When a client wants to fetch messages addressed to them:
  - Send the server a signature verifiable by their public key (stored on the server with their ephemeral ID)
  - If verified, fetch all messages addressed to their ephemeral ID.
  - Decrypt on client, and recheck it for tampering.
- Remember, all public keys fetched in this dynamic have to be trust-checked with multiple verified nodes.

## Trust Considerations
- Denial of Service by spamming the letterbox with messages -- fix with rate limits and verification
- Denial of Service by emptying the letterbox of messages -- fix by verifying users before they can pick up messages
- The same issue of the letterbox being emptied but server-side (malicious owner)
- Server refusing to verify signatures; denying availability to legitimate users.
- Malicious Key Substitution -- can we trust the server to return valid keys. This compromises the letterbox completely.

## Dev Notes
- XEdDSA signatures using ECDH (X3DH) keys using Curve25519.
- HMAC tags to verify message authenticity.
- Letterbox DB functions in [this folder](./src/main/java/com/novis/server/)
