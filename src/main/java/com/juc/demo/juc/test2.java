package com.juc.demo.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class  test2 {

    //创建固定值
    private static final int number = 7;
    public static void main(String[] args) {
        //创建CyclicBarrier
        CyclicBarrier cyclicBarrier =
                new CyclicBarrier(number,()->{
                    System.out.println("集齐七颗龙珠就可以召唤神龙");
                });
        //集齐七颗龙珠
        for(int i=1;i<=7;i++) {
            new Thread(()->{

                //等待
                try {
                    System.out.println(Thread.currentThread().getName()+"星龙被收集到了");

                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
