package com.juc.demo.redwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

// 演示读写锁降级
public class test1 {
    public static void main(String[] args) {
        //可重入读写锁对象
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

        //锁降级
        //1 获取到写锁
        writeLock.lock();
        System.out.println("writeLock.lock()");

        //2 获取到读锁  读的时候不能写 写的时候可以读
        readLock.lock();
        System.out.println("readLock.lock()");

        //3 释放写锁
        writeLock.unlock();

        //4 是否读锁
        readLock.unlock();
    }
}
