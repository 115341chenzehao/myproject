package com.czhhhh.demo.demos.thread;

import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

public class ResultThread {
    public static void main(String[] args) {
        System.out.println("----------1、Runnable 不能获取执行结果；使用线程池executorservice-----------");
        resultByRunnable();

        System.out.println("----------2、Callable -- 可以获取执行结果提交；Callable任务至ExecutorService执行，利用Futrue接收执行结果\n-----------");
        resultByCallable();

    }

    /**
     * 1、Runnable 不能获取执行结果
     * 使用线程池executorservice
     */
    public static void resultByRunnable() {
        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        // 创建一个Runnable任务
        Runnable rbTask = new Runnable() {
            @Override
            public void run() {
                System.out.println("This Thread is" + Thread.currentThread().getName());
            }
        };
        // 提交任务到executorService中执行
        for (int i = 0; i < 10; i++) {
            executorService.submit(rbTask);
        }
        // 关闭executorService,不接受新的任务，会等待之前所有的任务执行完成
        executorService.shutdown();
    }

    /**
     * 2、Callable -- 可以获取执行结果
     * 提交Callable任务至ExecutorService执行，利用Futrue接收执行结果
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void resultByCallable(){
        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        // 新建Callable任务
        Callable<String> caTask = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "This Thread is" + Thread.currentThread().getName();
            }
        };
        // 提交任务到ExecutorServie，并传给Future对象
        Future[] future = new Future[10];
        for (int i = 0; i < 10; i++) {
            future[i] = executorService.submit(caTask);
        }
        // 从Futrue中输出信息
        for (int i = 0; i < 10; i++) {

            try {
                System.out.println(future[i].get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        // 关闭executorService，停止提交任务，等待之前的任务完成
        executorService.shutdown();
    }
}
