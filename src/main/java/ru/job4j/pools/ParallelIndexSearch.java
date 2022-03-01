package ru.job4j.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelIndexSearch<T> extends RecursiveTask<Integer> {

    private final T[] arr;
    private final T key;
    private final int start;
    private final int end;

    public ParallelIndexSearch(T[] arr, T key, int start, int end) {
        this.arr = arr;
        this.key = key;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - start < 10) {
            return sort();
        }
        int mid = (start + end) / 2;
        ParallelIndexSearch<T> leftFind = new ParallelIndexSearch<>(arr, key, start, mid);
        ParallelIndexSearch<T> rightFind = new ParallelIndexSearch<>(arr, key, mid + 1, end);
        leftFind.fork();
        rightFind.fork();
        int leftIndex = leftFind.join();
        int rightIndex = rightFind.join();
        return Math.max(leftIndex, rightIndex);
    }

    private int sort() {
        for (int i = start; i < end; i++) {
            if (arr[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

    private static <T> int search(T[] array, T element) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelIndexSearch<>(array, element, 0, array.length - 1));
    }
}
