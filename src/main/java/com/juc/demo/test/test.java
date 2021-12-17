package com.juc.demo.test;

import com.juc.demo.DemoApplication;
import org.springframework.boot.SpringApplication;

public class test {
    public static void main(String[] args) {
        Thread aa = new Thread(() -> {
            /**
             * isDaemon() true : 守护线程
             *            false： 用户线程
             */
            System.out.println(Thread.currentThread().getName()
                    + "::" + Thread.currentThread().isDaemon());
            while (true) {

            }
        },"aa");
        // 设置守护线程
        aa.setDaemon(true);
        aa.start();
        System.out.println(Thread.currentThread().getName() + "OVER" );
    }
}
