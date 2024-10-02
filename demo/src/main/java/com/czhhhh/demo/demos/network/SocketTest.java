package com.czhhhh.demo.demos.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketTest {

    public static void main(String[] args) {
        System.out.println("--------1、Socket实例测试---------");
//        socketInstanceTest();

        System.out.println("--------2、服务器Socket测试（要现在cmd执行telnet ip 端口 启动socket监听）---------");
        serverSocketTest();

        System.out.println("----------3、通过单线程的方式，实现服务器socket 作用于多个客户端的功能---------");
        serverSocketByThread();//有点问题，后面再调
    }

    /**
     * Socket实例-测试
     */
    public static void socketInstanceTest() {
        try (Socket socket = new Socket("bbs.newsmth.net", 23)) {
            socket.setSoTimeout(10000);//设置超时时间
            InputStream inputStream = socket.getInputStream();
            Scanner scanner = new Scanner(inputStream, "gbk");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 服务器Socket模拟测试（telnet ip 端口）
     * 1、模拟服务器启用Socket监听连接
     * 2、客户端连接socket
     * 3、打印客户端输入信息、服务器输出信息
     */
    public static void serverSocketTest() {
        try (ServerSocket serverSocket = new ServerSocket(8888)) {
            //accept，服务器建立Socket，等待客户端发起连接
            Socket socket = serverSocket.accept();
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            /**
             1、客户端发起连接
             比如某个客户端，执行telnet 服务器ip 端口，就会将printwriter的内容用输出流写入socket中
             */
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(os, "gbk"), true);
            printWriter.println("我是客户端发起的socket连接，输入【exit】，将退出连接");

            /**
             2、服务端读取Socket数据，借助输入流、Scanner转换输入流内容输出
             *
             */
            Scanner scanner = new Scanner(is);
            boolean flag = true; //标记符，用于退出连接
            while (flag && scanner.hasNextLine()) {
                String s = scanner.nextLine();
                System.out.println(s);
                // 输入exit时，退出连接
                if ("exit".equals(s)) {
                    flag = false;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

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



