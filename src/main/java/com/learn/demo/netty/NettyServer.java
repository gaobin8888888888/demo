package com.learn.demo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpServerChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author gaobin
 * @date 2021/12/23 11:33 上午
 * @desc
 */
public class NettyServer {

    public static void main(String[] args) throws Exception {
        //处理连接请求
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //负责网络的读写
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            //创建服务器端的启动对象
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //使用链式编程配置参数
            serverBootstrap.group(bossGroup, workGroup)
                    // 设置非堵塞，用它建立新accept连接
                    .channel(NioServerSocketChannel.class)
                    // tcp/ip协议，初始化服务端可连接队列的最大长度
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new NettyServerHandler());
                        }
                    });
            System.out.println("netty server start success");
            // 绑定端口并生产异步对象
            ChannelFuture cf = serverBootstrap.bind(9000).sync();

            cf.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
