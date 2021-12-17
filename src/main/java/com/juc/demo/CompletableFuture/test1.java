package com.juc.demo.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 *
 * 自定义线程
 * https://www.wenjiangs.com/doc/x3zjh8pp
 *
 * java8 异步
 * https://www.cnblogs.com/xinde123/p/10928091.html
 *
 *
 */
//异步回调
public class test1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //异步调用 没有返回值
        CompletableFuture<Void> completableFuture01 = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName()+"：completableFuture01：");
        });
        completableFuture01.get();



        //异步调用 有返回值
        CompletableFuture<Integer> completableFuture02 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"completableFuture02");
            int i =100/0;
            return 1024;
        });
        completableFuture02.whenComplete((t,u)->{
            System.out.println("--t:"+t);
            System.out.println("--u:"+u);
        }).get();
    }
}
