package com.learn.demo.io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author gaobin
 * @date 2021/12/22 10:08 下午
 * @desc
 */
public class SocketServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8900);
        while (true) {
            System.out.println("wait line");
            Socket socket = serverSocket.accept();
            System.out.println("server line success");
            byte[] b = new byte[1024];
            int len = socket.getInputStream().read(b);
            if (len != -1) {
                String s = new String(b, 0, len);
                System.out.println("rev: " + s);
            }
            socket.getOutputStream().write("hello".getBytes(StandardCharsets.UTF_8));
            socket.getOutputStream().flush();
        }
    }
}
