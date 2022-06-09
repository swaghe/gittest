package com.example.controller;

import com.example.test.Test1;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author hjc
 * @Date 2022-05-17-15-15
 **/
@RestController
public class LockController {

    int count = 10;
    private static final Object o = new Object();

    @GetMapping("/buy")
    public void buy() throws InterruptedException {
//        synchronized (o) {
//        Thread.sleep(10);
        if (count <= 0) {
            System.out.println("商品没货了");
            return;
        }
        count--;
//        }
    }

    @GetMapping("getCount")
    public long get() {
        return count;
    }

    @GetMapping("setCount/{num}")
    public void set(@PathVariable int num) {
        count = count + num;
    }
}
