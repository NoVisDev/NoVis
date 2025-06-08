# Proposed Feature: Server Connection Tree System

## Overview

This feature adds a transparent, verifiable server-to-server trust structure. Each server must declare which other servers it connects to — forming a public **connection tree** that clients can inspect and verify. This helps prevent routing deception, improves trust transparency, and gives users a visual model of how their messages travel.

## Design Goals

- ✅ Make server relationships **auditable**
- 🔍 Let clients **verify trust paths** all the way to central or verified nodes
- 🛡️ Detect misconfigured or shady servers with no valid upward connection
- 🧠 Offer both end-users and security-focused users insight into routing

## Flow Summary

1. **Server Link Declarations**  
   - Servers publish their direct upstream/downstream peers.
   - They may also include a **signed statement** from each peer confirming the link.

2. **Client Trust Query**  
   - When a user connects, their client requests the full upstream connection path.
   - Each step must be **cryptographically verifiable** (e.g., signed by the linked node).

3. **Validation**  
   - Clients validate the trust path:
     - Are all signatures intact?
     - Does the path end at a verified hub (central or national)?
     - Are any expected connections missing?

4. **Visualization (Optional)**  
   - Clients can optionally display the path as a tree/graph.
   - Unverified/missing connections are marked clearly for user inspection.

5. **Warning System**  
   - If a server cannot prove a legitimate route to a known verified node:
     - Client shows a warning
     - Optionally blocks usage or limits feature access

## Trust Considerations

- 🧱 **Link Spoofing**  
  - Prevented via mutual signing: both nodes must agree they’re connected.

- 🕳️ **Hidden or Colluding Nodes**  
  - Mitigated via:
    - Independent trust nodes
    - Redundant cross-checks
    - Geo-diverse servers

- 🔄 **Dynamic Topology Changes**  
  - Servers that change their paths frequently without notice may be flagged.
  - Clients cache previous known good paths for comparison.

## Dev Notes

🛠️ **Useful Tools & Libs**:

- **Cryptographic Signatures**:
  - `Java Cryptography Architecture (JCA)`
  - `Bouncy Castle` (for ECC, X.509 signatures)

- **Graph Rendering (Optional UI)**:
  - `GraphStream` (Java)
  - `JavaFX Canvas` for custom graph drawing
  - `D3.js` if building hybrid app w/ embedded web views

- **Network Communication**:
  - `Netty` or `OkHttp` (for peer-to-peer trust data fetching)
  - `Jackson` or `Gson` for JSON trust structure parsing

