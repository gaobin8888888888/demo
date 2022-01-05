package com.learn.demo.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author gaobin
 * @date 2021/12/22 10:47 下午
 * @desc
 */
public class SocketServerNew {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8900));
        //非阻塞
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("start success");
        while (true) {
            selector.select();

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey s = iterator.next();
                if (s.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) s.channel();
                    SocketChannel socketChannel = serverSocketChannel1.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    System.out.println("client line success");
                } else if (s.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) s.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                    int len = socketChannel.read(byteBuffer);
                    if (len != -1) {
                        System.out.println("rev : " + new String(byteBuffer.array()));
                    } else {
                        socketChannel.close();
                        System.out.println("client close");
                    }
                }
                iterator.remove();
            }
        }
    }
}
