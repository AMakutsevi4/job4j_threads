package ru.job4j.pools;

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
            return sort(arr, key);
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

    private static <T> int sort(T[] arr, T key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }
}
