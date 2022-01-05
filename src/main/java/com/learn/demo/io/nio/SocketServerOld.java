package com.learn.demo.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author gaobin
 * @date 2021/12/22 10:47 下午
 * @desc
 */
public class SocketServerOld {

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8900));
        //非阻塞
        serverSocketChannel.configureBlocking(false);
        System.out.println("start success");
        List<SocketChannel> list = new ArrayList<>();
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel != null) {
                System.out.println("client connect success");
                socketChannel.configureBlocking(false);
                list.add(socketChannel);
            }
            Iterator<SocketChannel> iterator = list.iterator();
            while (iterator.hasNext()) {
                SocketChannel s = iterator.next();
                ByteBuffer byteBuffer = ByteBuffer.allocate(100);
                int read = s.read(byteBuffer);
                if (read > 0) {
                    System.out.println("rev : " + new String(byteBuffer.array()) + "       " + read);
                }
            }
        }
    }
}
