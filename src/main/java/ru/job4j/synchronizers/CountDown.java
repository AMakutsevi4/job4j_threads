package ru.job4j.synchronizers;

import java.util.concurrent.CountDownLatch;

public class CountDown {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch downLatch = new CountDownLatch(3);
        new Work(downLatch);
        new Work(downLatch);
        new Work(downLatch);

        downLatch.await();
        System.out.println("Все работы завершены");
    }
}

class Work extends Thread {
    CountDownLatch countDownLatch;

    public Work(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
        start();
    }

    @Override
    public void run() {
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Закончил свою работу");
        countDownLatch.countDown();
    }
}
