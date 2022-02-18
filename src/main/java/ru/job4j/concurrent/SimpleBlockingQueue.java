package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    private final int size;

    public SimpleBlockingQueue(int size) {
        this.size = size;
    }

    public void offer(T value) throws InterruptedException {
        while (queue.size() == size) {
            wait();
        }
        queue.add(value);
        notify();
    }

    public T poll() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        notify();
        return queue.remove();
    }
}