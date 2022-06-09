package com.example.test;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author hjc
 * @Date 2022-05-17-14-24
 **/
public class LockThreadTest {

    private static long count;

    public LockThreadTest(long count) {
        this.count = count;
    }

    public static void main(String[] args) throws InterruptedException {

        LockThreadTest test = new LockThreadTest(10);
        for (int i = 0; i < 50000; i++) {
            new Thread(() -> {
                if (test.getCount() > 0) {
                    test.buy(1);
                    System.out.println(Thread.currentThread().getName() + ":" + test.getCount());
                }
            }).start();
        }

    }

    public void buy(long n) {
        count -= n;
    }

    public long getCount() {
        return count;
    }


}
