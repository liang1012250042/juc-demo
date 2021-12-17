package com.juc.demo.list;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * list集合线程不安全
 */

public class test1 {
    public static void main(String[] args) {
        //创建ArrayList 并发插入问题
//        List<String> list = new ArrayList<>();

        // Vector解决
//        List<String> list = new Vector<>();

        //通过Collections工具类解决
//        List<String> list = Collections.synchronizedList(new ArrayList<>());

        // 写时复制技术
        List<String> list = new CopyOnWriteArrayList<>();

        for(int i=0;i<10;i++) {
            new Thread(()->{
                //向集合添加内容
                list.add(UUID.randomUUID().toString().substring(0,8));
                //从集合获取内容
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
