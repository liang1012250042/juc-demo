package com.juc.demo.sync;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 1.创建资源类，定义属性和操作方法
 *
 * 2.创建多个线程 调用资源类的操作方法
 */

class LTicket {
    // 票数
    private int number = 30;

    // 创建可重入锁 true 公平锁  false 非公平锁
    private final ReentrantLock lock = new ReentrantLock(true);
    //操作方法：卖票
    public void sale() {
        lock.lock();
        //判断：是否有票
        try {
            if(number > 0) {
                System.out.println(Thread.currentThread().getName() +
                        ":卖出："+ (number--) + "剩下" + number);
            }
        } finally {
            lock.unlock();
        }
    }
}

public class SaleLTicket {
    public static void main(String[] args) {
        //创建ticket对象
        LTicket ticket = new LTicket();

        // 创建三个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                //调用买票方法
                for(int i=0;i<40;i++) {
                    ticket.sale();
                }
            }
        },"aa").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //调用买票方法
                for(int i=0;i<40;i++) {
                    ticket.sale();
                }
            }
        },"bb").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //调用买票方法
                for(int i=0;i<40;i++) {
                    ticket.sale();
                }
            }
        },"cc").start();
    }
}
