# Feature: Packet Frame Decoders

## Overview
Turns byte data of packets (and their relative data) in NoVis into manipulatable classes

## Design Goals
- Allow easy management of packets, without having to delve into bytedata.
- Form packet data, so headers and the payload can easily be separated

## Flow Summary
- Client/Server creates a packet.
- Sends it to the frame encoder
- This turns it into bytedata
- Bytedata sent over a network (TCP/IP)
- Received in the frame decoder
- Turned into the same packet that was sent over the network
- Managed by the server/client.

## Trust Considerations
- Bad packets sent, causing the decoder to fail to recognize packets, especially if there's modifications to user-run servers/clients. Packet format needs to be enforced.

## Dev Notes
- [PacketFrameDecoder](./src/main/java/com/novis/common/net/PacketFrameDecoder.java)
- [PacketFrameEncoder](./src/main/java/com/novis/common/net/PacketFrameEncoder.java)
