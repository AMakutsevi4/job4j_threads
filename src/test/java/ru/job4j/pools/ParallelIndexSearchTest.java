package ru.job4j.pools;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ParallelIndexSearchTest {

    @Test
    public void whenTestTrueMeaning() {
        Integer[] array = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int rsl = ParallelIndexSearch.search(array, 8);
        assertEquals(rsl, 7);
    }

    @Test
    public void whenTestFalseMeaning() {
        Integer[] array = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int rsl = ParallelIndexSearch.search(array, 12);
        assertEquals(rsl, -1);
    }
}
