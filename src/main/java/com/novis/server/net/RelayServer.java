package com.novis.server.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RelayServer {
    private final int port;
    private static final Logger logger = LoggerFactory.getLogger(RelayServer.class);

    public RelayServer(int port) { // init server
        this.port = port;
    }

    public void start() throws Exception { // start server
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // handles accepting traffic
        EventLoopGroup workerGroup = new NioEventLoopGroup(); // worker handles traffic

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class) // uses a non-blocking NIO socket
                    .childHandler(new RelayServerInitializer()); // handles I/O

            ChannelFuture f = b.bind(port).sync(); // start server
            logger.info("Server up on port: " + port);
            f.channel().closeFuture().sync(); // Wait for server channel to close
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
