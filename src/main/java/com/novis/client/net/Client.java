package com.novis.client.net;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client {
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
            while (true) {
                System.out.print("> ");
                line = scanner.nextLine();

                logger.info("User input: {}", line);

                if ("exit".equalsIgnoreCase(line)) break;
                channel.writeAndFlush(line + "\r\n"); // send it to the server
            }

            channel.close().sync(); // close client connection
        } finally {
            group.shutdownGracefully();
        }
    }
}
