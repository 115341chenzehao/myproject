package com.czhhhh.demo.demos.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketTest {

    public static void main(String[] args) {
        System.out.println("----------3、通过单线程的方式，实现服务器socket 作用于多个客户端的功能---------");
        serverSocketByThread();//有点问题，后面再调
    }

    /**
     * 3、通过单线程的方式，实现服务器socket 作用于多个客户端的功能
     *
     */
    public static void serverSocketByThread() {
        try (ServerSocket serverSocket = new ServerSocket(8889)) {
            //只有代码停止运行，socket监听才会停止
            while (true) {
                Socket socket = serverSocket.accept();
                /**
                 * 启动线程，用线程处理--
                 * 1、客户端发起socket连接时，执行的操作;
                 * 2、读取socket内容的操作；
                 */
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try (OutputStream os = socket.getOutputStream(); InputStream is = socket.getInputStream()) {
                            //客户端连接socket、打印socket操作
                            serverSocket02(os, is);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 3.1 单线程--用于执行客户端sokcet的output流；用于输出流 内容
     * @param os,is
     */
    public static void serverSocket02(OutputStream os, InputStream is) throws UnsupportedEncodingException {
        Scanner scanner = new Scanner(is);
        //客户端连接socket
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(os, "gbk");
        PrintWriter printWriter = new PrintWriter(outputStreamWriter, true);
        printWriter.print("有客户端发起了Socket连接了。。。");
        //读取socket信息

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
        }
    }


}



