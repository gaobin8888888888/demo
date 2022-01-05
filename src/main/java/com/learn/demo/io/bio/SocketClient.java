package com.learn.demo.io.bio;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author gaobin
 * @date 2021/12/23 10:05 上午
 * @desc
 */
public class SocketClient {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Socket socket = new Socket("127.0.0.1", 8900);
            String next = scanner.next();
            socket.getOutputStream().write(next.getBytes(StandardCharsets.UTF_8));
            socket.getOutputStream().flush();
            System.out.println("send over");

            byte[] b = new byte[1024];
            int read = socket.getInputStream().read(b);
            if (read > 0) {
                System.out.println("rev:" + new String(b, 0, read, StandardCharsets.UTF_8));
            }
        }
    }
}
