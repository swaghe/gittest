package com.example.test;

/**
 * @Author hjc
 * @Date 2022-05-16-19-46
 **/
public class ThreadTest01 {


    public static void main(String[] args) {

        //线程按顺序执行可以使用join，他的源码就是首先while（判断a线程是否还存活），如果还存活，就让b线程一直等待下去，直到a线程销毁
        Thread one = new Thread(() -> {
            System.out.println(": 第一个执行");
        });

        Thread two = new Thread(() -> {
            try {
                one.join();
                System.out.println(": 第二个执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        Thread theed = new Thread(() -> {
            try {
                two.join();
                System.out.println(": 第三个执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        one.start();
        two.start();
        theed.start();


    }
}
