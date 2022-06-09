package com.example.test;

import lombok.SneakyThrows;

/**
 * @Author hjc
 * @Date 2022-05-16-20-58
 **/
public class FlagThreadTest extends Thread {

    private boolean flag = true;

    @SneakyThrows
    @Override
    public void run() {
        int count = 1;
        while (flag) {
            System.out.println("第" + count + "次执行");
            Thread.sleep(1000);
            count++;
        }
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public static void main(String[] args) throws InterruptedException {
        FlagThreadTest threadTest = new FlagThreadTest();

        threadTest.start();
        Thread.sleep(4000);
        threadTest.setFlag(false);
    }
}
