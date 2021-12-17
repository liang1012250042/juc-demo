package com.juc.demo.juc;

import java.util.concurrent.CountDownLatch;

// 演示 CountDownLatch
// 六个同学离开之后才可以关门
public class test1 {
    public static void main(String[] args) throws InterruptedException {

        //创建CountDownLatch对象，设置初始值
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for(int i=1;i<=6;i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+" 号出门");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }

        //等待
        countDownLatch.await();

        System.out.println(Thread.currentThread().getName()+"班长锁门，走人");
    }
}
