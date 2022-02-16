package ru.job4j.transfer;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class UserStorageTest {
    @Test
    public void whenAdd() {
        User one = new User(1, 100);
        User two = new User(2, 50);
        UserStorage storage = new UserStorage();
        storage.add(one);
        storage.add(two);
        storage.transfer(1, 2, 50);
        assertEquals(50, one.getAmount());
        assertEquals(100, two.getAmount());
    }
}