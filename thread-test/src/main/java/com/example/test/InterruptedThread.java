package com.example.test;

import lombok.SneakyThrows;

/**
 * @Author hjc
 * @Date 2022-05-16-22-50
 **/
public class InterruptedThread extends Thread {

    @SneakyThrows
    @Override
    public void run() {
        int i = 1;
        while (true) {

            boolean flag = Thread.currentThread().isInterrupted();
//            Thread.sleep(1000);
            System.out.println("第" + i + "次执行");
            if (flag) {
                System.out.println("线程结束！" + flag);
                break;
            }
            i++;

        }
    }


    public static void main(String[] args) throws InterruptedException {
        InterruptedThread thread = new InterruptedThread();
        thread.start();
        Thread.sleep(50);
        thread.interrupt();
    }
}
