package com.juc.demo.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

//比较两个接口
//实现runnable接口
class MyThread1 implements Runnable {

    @Override
    public void run() {

    }
}

//实现Callable接口
class MyThread2 implements Callable {

    @Override
    public Object call() throws Exception {
        return 200;
    }
}
public class test1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //runnable接口
        new Thread(new MyThread1(),"aa").start();

        //callable接口
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread2());

        //lam表达式
        FutureTask<Integer> futureTask1 = new FutureTask<>(()->{
            System.out.println(Thread.currentThread().getName()+" come in callable");
            return 1024;
        });

        //创建一个线程
        new Thread(futureTask1,"lucy").start();

        while (!futureTask1.isDone()) {
            System.out.println("wait");
        }

        //调用
        System.out.println(futureTask1.get());

        System.out.println(Thread.currentThread().getName()+" come over");

    }
}
