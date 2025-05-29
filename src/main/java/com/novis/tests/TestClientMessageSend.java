package com.novis.tests;

import com.novis.common.net.Message;
import com.novis.common.net.PacketBuilder;
import com.novis.common.packet.PacketType;
import com.novis.common.packet.data.LetterboxPullPacketData;
import com.novis.common.packet.data.MessageDeliveryPacketData;
import com.novis.common.serializer.SerializationHelper;
import com.novis.common.utils.TestAction;
import io.netty.channel.Channel;

import java.util.Scanner;

public class TestClientMessageSend implements TestAction {
    /*
    * Explanation of THIS test
    *
    * - sends a packet containing the message to server, MESSAGE, MessageDeliveryPacketData
    * - server stores it in DB
    * - server sends back an ACKNOWLEDGE packet
    * */

    @Override
    public void run(Object[] args) {
        Channel channel = (Channel) args[0];
        Scanner scanner = new Scanner(System.in);

        while (true) {
            Message message = new Message("Test message", "owner", false);
            byte[] serialized = SerializationHelper.serialize(message);

            MessageDeliveryPacketData messageData = new MessageDeliveryPacketData("test_user", serialized);

            channel.writeAndFlush(PacketBuilder.buildPacket(PacketType.MESSAGE, messageData));

            if (scanner.nextLine() != null) {break;}
            // to make sure we don't end the program early
        }
    }
}
