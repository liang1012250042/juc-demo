package com.juc.demo.CustomThread;

import java.util.concurrent.*;

// 自定义线程池
public class test1 {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        try {
            //10个顾客请求
            for (int i = 1; i <= 10; i++) {
                // 执行
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + ": 办理业务 :");
                });
            }
        } catch (Exception e){
            e.getMessage();
        }finally {
            threadPool.shutdown();
        }
    }
}
