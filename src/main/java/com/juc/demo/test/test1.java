package com.juc.demo.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 第一步 创建一个资源类
class ShareResource {
    // 定义标志位
    private int flag = 1; // 1 aa 2 bb 3 cc

    //创建Lock锁
    private Lock lock = new ReentrantLock();

    // 创建三个condition
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    //打印五次 参数第几轮
    public void print5(int loop) throws InterruptedException {
        //上锁
        lock.lock();
        try {
            // 判断
            while (flag !=1) {
                c1.await();
            }
            for(int i=1;i<=5;i++) {
                System.out.println(Thread.currentThread().getName()+"::"+ i + "：轮数：" + loop);
            }
            flag = 2;
            c2.signal();
        }finally {
            // 释放锁
            lock.unlock();
        }
    }

    //打印十次 参数第几轮
    public void print10(int loop) throws InterruptedException {
        //上锁
        lock.lock();
        try {
            // 判断
            while (flag !=2) {
                c2.await();
            }
            for(int i=1;i<=10;i++) {
                System.out.println(Thread.currentThread().getName()+"::"+ i + "：轮数：" + loop);
            }
            flag = 3;
            c3.signal();
        }finally {
            // 释放锁
            lock.unlock();
        }
    }

    //打印十五次 参数第几轮
    public void print15(int loop) throws InterruptedException {
        //上锁
        lock.lock();
        try {
            // 判断
            while (flag !=3) {
                c3.await();
            }
            for(int i=1;i<=15;i++) {
                System.out.println(Thread.currentThread().getName()+"::"+ i + "：轮数：" + loop);
            }
            flag = 1;
            c1.signal();
        }finally {
            // 释放锁
            lock.unlock();
        }
    }
}
public class test1 {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for(int i=1;i<=10;i++) {
                try {
                    shareResource.print5(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"aa").start();

        new Thread(() -> {
            for(int i=1;i<=10;i++) {
                try {
                    shareResource.print10(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"bb").start();

        new Thread(() -> {
            for(int i=1;i<=10;i++) {
                try {
                    shareResource.print15(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"cc").start();
    }
}
