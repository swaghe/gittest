package com.example.test;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author hjc
 * @Date 2022-05-17-00-57
 **/
public class ThreadPoolTest {

    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 10
                , 60L, TimeUnit.SECONDS, new ArrayBlockingQueue(50));
        for (int i = 0; i < 100; i++) {
            int count = i;
            poolExecutor.execute(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + ": " + count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        poolExecutor.shutdown();

    }
}
