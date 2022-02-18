package ru.job4j.concurrent;


import org.junit.Test;

public class SimpleBlockingQueueTest {

    @Test
    public void whenQueueBlockingThenOne() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        Thread first = new Thread(() -> {
            try {
                queue.offer(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread second = new Thread(
                () -> {
                    try {
                        queue.poll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
        );
        first.start();
        second.start();
        first.join();
        second.join();
    }

    @Test
    public void whenQueueBlockingThenTwo() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        Thread first = new Thread(
                () -> {
                    int i = 0;
                    while (i < 10) {
                        i++;
                        try {
                            queue.offer(4);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        Thread second = new Thread(
                () -> {
                    try {
                        queue.poll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
        );
        first.start();
        second.start();
        first.join();
        second.join();
    }


    @Test
    public void whenQueueBlockingThenThree() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        Thread first = new Thread(
                () -> {
                    int i = 0;
                    while (i < 12) {
                        i++;
                        try {
                            queue.offer(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        Thread second = new Thread(
                () -> {
                    int i = 0;
                    while (i < 6) {
                        i++;
                        try {
                            queue.poll();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        first.start();
        second.start();
        first.join();
        second.join();
    }
}