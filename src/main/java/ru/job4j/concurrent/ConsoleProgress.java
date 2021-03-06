package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(100000);
        progress.interrupt();
    }

    @Override
    public void run() {
        try {
            int index = 0;
            char[] process = new char[]{'\\', '|', '/'};
            while (!Thread.currentThread().isInterrupted()) {
                System.out.print("\r load: " + process[index++]);
                if (index == process.length) {
                    index = 0;
                }
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().isInterrupted();
        }

    }
}

