package com.novis.tests;

import com.novis.common.net.PacketBuilder;
import com.novis.common.packet.PacketType;
import com.novis.common.packet.data.LetterboxPullPacketData;
import com.novis.common.utils.TestAction;
import io.netty.channel.Channel;

import java.util.Scanner;

public class TestClientLetterboxPull implements TestAction {
    /*
    * Explanation for this test
    *
    * - sends a letterbox pull request
    * - server receives it, collects all messages into a letterbox pull response
    * - client receives it, displays messages (or processes them), sends a letterbox delete request
    * - server receives letterbox delete request, honors it, deletes the letterbox contents for THAT recipient ID
    * - sends back an ACKNOWLEDGE.
    *
    * what you should notice:
    * - if you have sent a message or inserted data into the DB.
    * - first run: messages are displayed, ends with an ACK
    * - second run: no messages are displayed, ends with an ACK.
    * why? because it got deleted.
    * */

    @Override
    public void run(Object[] args) {
        Channel channel = (Channel) args[0];
        Scanner scanner = new Scanner(System.in);

        while (true) {
            LetterboxPullPacketData lppd = new LetterboxPullPacketData("test_user");

            channel.writeAndFlush(PacketBuilder.buildPacket(PacketType.LETTERBOX_PULL, lppd));

            if (scanner.nextLine() != null) {break;}
                // to make sure we don't end the program early
        }
    }
}
