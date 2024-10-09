package com.czhhhh.demo.demos.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MoreThreads {

    public static void main(String[] args) {
        System.out.println("----------1、线程的实现方式一:继承Thread类-----------");
//        moreThreadTest();

        System.out.println("----------2、线程的实现方式二:实现Runnable接口（推荐--解耦、非单继承）-----------");
//        myRunnableTest();

        System.out.println("----------3、线程的实现方式三:实现Callable接口-----------");
        String s = myCallableTest();
        System.out.println(s);
    }

    /**
     * 1、线程的实现方式一:继承Thread类
     */
    public static void moreThreadTest(){
        // 创建MyThread对象
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();
        // 设置线程明知
        t1.setName("线程1");
        t2.setName("线程2");
        t3.setName("线程3");
        // 启动线程
        t1.start();
        t2.start();
        t3.start();
    }

    /**
     * 2、线程的实现方式二:实现Runnable接口（推荐--解耦、非单继承）
     */
    public static void myRunnableTest(){
        MyRunnable myRunnable = new MyRunnable();
        Thread t1 = new Thread(myRunnable,"张三");
        Thread t2 = new Thread(myRunnable,"李四");
        Thread t3 = new Thread(myRunnable, "王五");

        t1.start();
        t2.start();
        t3.start();
    }

    /**
     * 3、线程的实现方式三:实现Callable接口
     * 和Runnable非常像，但Callable可以返回结果
     * @return
     */
    public static String myCallableTest(){
        // 创建异步任务
        FutureTask<String> task = new FutureTask<String>(new CallableTask());
        // 启动线程
        new Thread(task).start();
        String result = "";
        try {
            // 等待执行后，获取并返回结果
            result = task.get();
            return result;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            return result;
        }
    }


}

class MyThread extends Thread{
    @Override
    public void run(){
        for (int i = 0; i < 10; i++) {
            System.out.println(getName()+":打了"+i+"个小兵");
        }
    }
}


class MyRunnable implements Runnable{
    @Override
    public void run(){
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"执行了第"+i+"步");
        }
    }
}

class CallableTask implements Callable<String>{
    @Override
    public String call() throws Exception {
        return "hello,i am callable";
    }
}
