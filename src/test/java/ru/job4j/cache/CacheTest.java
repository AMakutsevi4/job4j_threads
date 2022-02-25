package ru.job4j.cache;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class CacheTest {

    @Test
    public void add() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0);
        Base base2 = new Base(2, 1);
        cache.add(base1);
        assertTrue(cache.add(base2));
    }

    @Test
    public void addFalse() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0);
        Base base2 = new Base(2, 1);
        cache.add(base1);
        cache.add(base2);
        assertFalse(cache.add(base1));
    }

    @Test
    public void update() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0);
        Base base2 = new Base(2, 1);
        cache.add(base1);
        cache.add(base2);
        Base base3 = new Base(2, 1);
        Base base4 = new Base(2, 2);
        assertTrue(cache.update(base3));
        assertFalse(cache.add(base4));
    }

    @Test(expected = OptimisticException.class)
    public void updateException() throws OptimisticException {
        Cache map = new Cache();
        Base base1 = new Base(1, 0);
        Base base2 = new Base(1, 3);
        map.add(base1);
        map.update(base2);
    }

    @Test
    public void delete() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0);
        Base base2 = new Base(2, 1);
        cache.add(base1);
        cache.add(base2);
        cache.delete(base2);
        assertTrue(cache.add(base2));
    }
}