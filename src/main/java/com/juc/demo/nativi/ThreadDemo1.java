package com.juc.demo.nativi;

// 第一步 创建资源类 定义属性和操作方法
class Share {
    //初始值
    private int number = 0;
    // +1 的方法
    public synchronized void incr() throws InterruptedException {
        while (number != 0) {
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName()+"::"+number);
        //通知
        this.notifyAll();
    }

    // -1 的方法
    public synchronized void decr() throws InterruptedException {
        while (number != 1) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"::"+number);
        //通知
        this.notifyAll();
    }
}
public class ThreadDemo1 {
    public static void main(String[] args) {
        Share share = new Share();
        new Thread(() -> {
            for (int i=1;i<=10;i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"aa").start();

        new Thread(() -> {
            for (int i=1;i<=10;i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"bb").start();

        new Thread(() -> {
            for (int i=1;i<=10;i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"cc").start();

        new Thread(() -> {
            for (int i=1;i<=10;i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"dd").start();
    }
}
