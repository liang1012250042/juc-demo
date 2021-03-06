package com.juc.demo.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class test3 {

    //三辆车抢六个车位
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for(int i = 1;i<=6;i++) {
            new Thread(()->{
                try {
                    //抢占
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+" 抢到了车位");
                    //随机停车时间
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));

                    System.out.println(Thread.currentThread().getName()+" 离开了车位");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                  semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
