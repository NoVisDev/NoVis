package com.novis.client.net;

import com.novis.common.net.PacketBuilder;
import com.novis.common.packet.PacketType;
import com.novis.common.packet.data.LetterboxPullPacketData;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client {
    // TODO: CLEANUP MESSAGE -> PACKET TRANSITION
    // TODO: HOW TO SEND RECIPIENT ID FIRST SEPARATE FROM MESSAGE

    private final String host;
    private final int port;

    private static final Logger logger = LoggerFactory.getLogger(Client.class);

    public Client(String host, int port) { // initialize our client with host and port of server
        this.host = host;
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup(); // worker?

        try {
            Bootstrap bootstrap = new Bootstrap(); // slap on all the server related things
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientInitializer());

            Channel channel = bootstrap.connect(host, port).sync().channel();

            // get input from terminal
            java.util.Scanner scanner = new java.util.Scanner(System.in);

            while (true) {
                LetterboxPullPacketData lppd = new LetterboxPullPacketData("cba");

                channel.writeAndFlush(PacketBuilder.buildPacket(PacketType.LETTERBOX_PULL, lppd)); // send it to the server
                if (scanner.nextLine() != null) {break;}
            }

            channel.close().sync(); // close client connection
        } finally {
            group.shutdownGracefully();
        }
    }
}
