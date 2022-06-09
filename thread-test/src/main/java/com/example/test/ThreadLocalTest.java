package com.example.test;

/**
 * @Author hjc
 * @Date 2022-05-17-00-38
 **/
public class ThreadLocalTest {

    public static void main(String[] args) {

        new Thread(() -> {
            TokenHolder.setToken(Thread.currentThread().getName() + ":user1");
            System.out.println(TokenHolder.getToken());
            TokenHolder.clear();
        }, "t1").start();
        new Thread(() -> {
            TokenHolder.setToken(Thread.currentThread().getName() + ":user2");
            System.out.println(TokenHolder.getToken());
            TokenHolder.clear();
        }, "t2").start();
        new Thread(() -> {
            TokenHolder.setToken(Thread.currentThread().getName() + ":user3");
            System.out.println(TokenHolder.getToken());
            TokenHolder.clear();
        }, "t3").start();
    }
}
