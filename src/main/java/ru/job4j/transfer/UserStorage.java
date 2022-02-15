package ru.job4j.transfer;

import net.jcip.annotations.GuardedBy;

import java.util.HashMap;
import java.util.Map;

public class UserStorage {
/**
    @GuardedBy("this")
    private final Map<Integer, User> usersList = new HashMap<>();

    public synchronized boolean add(User user) {
        return usersList.put(user.getId(), user);
    }

    public synchronized boolean update(User user) {
        return usersList.replace(user.getId(), user);
    }

    public synchronized boolean delete(User user) {
        return usersList.remove(user.getId(), user);
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        if (usersList.get(fromId) != null && usersList.get(toId) != null
                && usersList.get(fromId).getAmount() >= amount) {
             update(usersList.get(fromId).getAmount() - amount);
             update(usersList.get(toId).getAmount() + amount);
            return true;

        }
        return false;
    }

    public static void main(String[] args) {
        UserStorage storage = new UserStorage();
        storage.add(new User(1, 100));
        storage.add(new User(2, 200));
        storage.transfer(1, 2, 50);
    }
*/
}
