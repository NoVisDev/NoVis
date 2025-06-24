package com.novis.tests;

import com.novis.common.net.Message;
import com.novis.common.net.PacketBuilder;
import com.novis.common.packet.PacketType;
import com.novis.common.packet.data.KeyExchangePacketData;
import com.novis.common.packet.data.MessageDeliveryPacketData;
import com.novis.common.serializer.SerializationHelper;
import com.novis.common.utils.TestAction;
import com.novis.crypto.key.ECDHGenerator;
import com.novis.crypto.key.ECDHKeySuite;
import io.netty.channel.Channel;

import java.util.Scanner;

public class TestClientReceiveKEP implements TestAction {
    @Override
    public void run(Object[] args) {
        Channel channel = (Channel) args[0];

        ECDHKeySuite keySuite = ECDHGenerator.generateKeyPair();

        assert keySuite != null;
        byte[] publicKey = keySuite.getX25519().getPublicKey().getAsBytes();

        KeyExchangePacketData kepd = new KeyExchangePacketData(publicKey, "you", "me");

        channel.writeAndFlush(PacketBuilder.buildPacket(PacketType.KEY_EXCHANGE, kepd));
    }

    // sends a Key Exchange Packet to the server to test the database / reception of the packet
    // TODO: implement ECDH fully

}
