package com.czhhhh.demo.demos.io;

import ch.qos.logback.classic.net.SimpleSocketServer;

import java.io.*;

public class FileIoTest {


    public static void main(String[] args) throws IOException {

        //1.通过字节流，读取带有中文的文件--乱码现象
        System.out.println("----------1.通过字节流，获取带有中文的文件--乱码现象---------");
        String s = fileInputFromStream();
        System.out.println(s);

        //2.通过字节流，读取带有中文的文件--解决乱码现象
        System.out.println("----------2.通过字节流，获取带有中文的文件--解决乱码现象---------");
        String s2 = fileInputFromStream02();
        System.out.println(s2);

        //3.通过字符流，读取文件
        System.out.println("----------3.通过字符流，读取文件---------");
        String s3 = fileReaderText();
        System.out.println(s3);

        //4.通过字符流，写入文件
        System.out.println("----------4.通过字符流，写入文件---------");
        fileWriteText();

        //5.文件复制
        System.out.println("----------5.文件复制---------");
        copyFile();

        //6.利用Buffered缓存流 快速复制大文件
        System.out.println("----------6.利用Buffered缓存流 快速复制大文件---------");
        copyFileByBuffer();

    }

    /**
     * 1.字节流读取中文文件--乱码
     * 也有办法解决，但是不建议用字节流，字节流编码是ACSII码，而中文是utf-8
     * 字节流可以处理所有文件，字符流只能处理文本
     * @return
     * @throws IOException
     */

    public static String fileInputFromStream() throws IOException {
        File fileio = new File("D:\\Program Files (x86)\\Study\\myproject\\demo\\src\\main\\resources\\static\\iofile.txt");
        try (FileInputStream fileInputStream = new FileInputStream(fileio)) {
            int len;
            int sum = 0;
            StringBuffer stringBuffer = new StringBuffer();
            while ((len = fileInputStream.read()) != -1) {
                stringBuffer.append((char) len);
                sum++;
            }
            return stringBuffer.toString();
        }
    }

    /**
     * 2.字节流读取中文文件--解决乱码
     * 利用了缓存区byte[]和String构造方法（StringCoding.decode进行了编码处理）
     *
     * @return
     * @throws IOException
     */
    public static String fileInputFromStream02() throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream("D:\\Program Files (x86)\\Study\\myproject\\demo\\src\\main\\resources\\static\\iofile.txt")
        ) {
            byte[] b = new byte[1024];
            int len;
            String s = "";
            while ((len = fileInputStream.read(b)) != -1) {
                s += new String(b, 0, len);
            }
            return s;
        }
    }


    /**
     * 3、字符输入流-读取文件内容
     * read还有其他重载方法-可以实现读取文件指定内容，这里不演示了
     *
     * @return
     * @throws IOException
     */
    public static String fileReaderText() throws IOException {
        try (FileReader fileReader = new FileReader("D:\\Program Files (x86)\\Study\\myproject\\demo\\src\\main\\resources\\static\\iofile.txt");
        ) {
            StringBuffer stringBuffer = new StringBuffer();
            int word;
            while ((word = fileReader.read()) != -1) {
                stringBuffer.append((char) word);
            }
            return stringBuffer.toString();
        }
    }

    /**
     * 4、字符流写入文件操作
     * flush（）方法会立刻将FileWrite缓冲区中的数据，立刻写入文件，并且不会关闭流
     * 否则缓冲区的数据，只有流执行close后，才会写入文件中
     *
     * @throws IOException
     */
    public static void fileWriteText() throws IOException {
        File file = new File("D:\\Program Files (x86)\\Study\\myproject\\demo\\src\\main\\resources\\static\\writeDest.txt");
        try (FileWriter fileWriter = new FileWriter(file);
        ) {
            fileWriter.write("我是加入的内容；");
            fileWriter.flush(); //执行刷新操作
            fileWriter.write("我是执行flush刷新后，将缓冲区内容写入文件后，新加的内容");
        }

    }


    /**
     * 5、使用字符输入流、字符输出流，实现文件复制
     *
     * @throws IOException
     */
    public static void copyFile() throws IOException {
        long startTime = System.currentTimeMillis();
        FileReader fileSource = null;
        FileWriter fileDest = null;
        try {
            fileSource = new FileReader("D:\\Program Files (x86)\\Study\\myproject\\demo\\src\\main\\resources\\static\\source.txt");
            fileDest = new FileWriter("D:\\Program Files (x86)\\Study\\myproject\\demo\\src\\main\\resources\\static\\dest.txt");
            //读取source,写入dest
            int word;
            while ((word = fileSource.read()) != -1) {
                fileDest.write(word);
            }
        } finally {
            if (fileSource != null) {
                fileSource.close();
            }
            if (fileDest != null) {
                fileDest.close();
            }
        }
        long endTime = System.currentTimeMillis();//计算结束时间
        System.out.println("文件复制耗时:" + (endTime - startTime) + "毫秒");
    }


    /**
     * 6、利用缓存流Buffered快速提升文件复制的效率，比如复制mp3
     * BufferedInputStream、BufferedOutputStream 处理的是 字节流；（字节流可以处理所有文件）
     * @throws IOException
     */
    public static void copyFileByBuffer() throws IOException {
        long startTime = System.currentTimeMillis();
        FileInputStream sourceStream = null;
        FileOutputStream destStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        int word;
        sourceStream = new FileInputStream("D:\\Program Files (x86)\\Study\\myproject\\demo\\src\\main\\resources\\static\\mp4\\sourceVideo.mp4");
        destStream = new FileOutputStream("D:\\Program Files (x86)\\Study\\myproject\\demo\\src\\main\\resources\\static\\mp4\\destVideo.mp4");
        bufferedInputStream = new BufferedInputStream(sourceStream);
        bufferedOutputStream = new BufferedOutputStream(destStream);
        while ((word = bufferedInputStream.read()) != -1) {
            bufferedOutputStream.write(word);
        }
        long endTime = System.currentTimeMillis();//计算结束时间
        System.out.println("文件复制耗时:" + (endTime - startTime) + "毫秒");
    }


}
