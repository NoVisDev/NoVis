package com.novis.client.net;

import com.novis.common.Message;
import com.novis.common.Packet;
import com.novis.common.SerializationHelper;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

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
            String line = ""; // for input
            Message message = new Message();
            while (true) {
                System.out.print("> ");
                line = scanner.nextLine();

                logger.info("User input: {}", line);

                message = new Message(line, "abc", "my random nigga", true);
                byte[] serialized = SerializationHelper.serialize(message);

                if ("exit".equalsIgnoreCase(line)) break;
                channel.writeAndFlush(new Packet((short) 0x4E56, (byte) 0x10, (byte) 1, serialized.length, serialized)); // send it to the server
            }

            channel.close().sync(); // close client connection
        } finally {
            group.shutdownGracefully();
        }
    }
}
