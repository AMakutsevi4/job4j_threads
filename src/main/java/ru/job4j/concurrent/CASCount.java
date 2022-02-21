package ru.job4j.concurrent;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public CASCount(int value) {
        this.count.set(value);
    }

    public void increment() {
        int value;
        int nextValue;
        do {
            value = count.get();
            nextValue = value + 1;
        } while (!count.compareAndSet(value, nextValue));
    }

    public int get() {
        return count.get();
    }

    public static void main(String[] args) {
        CASCount count = new CASCount(4);
        count.increment();
        System.out.println(count.get());
        count.increment();
        System.out.println(count.get());
        count.increment();
        System.out.println(count.get());
    }
}
