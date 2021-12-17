package com.juc.demo.redwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//资源类
class MyCache {
    // 创建map集合
    private volatile Map<String,Object> map = new HashMap<>();

    //创建读写锁
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    //暂停一会
    public void put(String key,Object value) {
        //添加写锁
        readWriteLock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName()+"：正在写操作："+key);

            TimeUnit.MICROSECONDS.sleep(300);

            //放数据
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+":写完了:"+key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }



    //取数据
    public Object get(String key) {
        //添加读锁
        readWriteLock.readLock().lock();
        Object result = null;
        try {


            System.out.println(Thread.currentThread().getName()+":正在读取操作:"+key);

            TimeUnit.MICROSECONDS.sleep(300);

            //取数据
            result = map.get(key);
            System.out.println(Thread.currentThread().getName()+":取完了:"+key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }


        return result;

    }
}
public class ReadWriteLock1 {
    public static void main(String[] args) {

        MyCache myCache = new MyCache();

        // 创建线程放数据
        for(int i=1;i<=5;i++) {

            final int num = i;

            new Thread(()->{
                myCache.put(num+"",num+"");
            },String.valueOf(i)).start();

        }

        // 创建线程取数据
        for(int j=1;j<=5;j++) {
            final int num = j;
            new Thread(()->{
                myCache.get(num+"");
            },String.valueOf(j)).start();

        }

    }
}
