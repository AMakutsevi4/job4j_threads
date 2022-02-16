package ru.job4j.transfer;

import net.jcip.annotations.GuardedBy;

import java.util.HashMap;
import java.util.Map;

public class UserStorage {

    @GuardedBy("this")
    private final Map<Integer, User> usersList = new HashMap<>();

    public synchronized boolean add(User user) {
        return usersList.putIfAbsent(user.getId(), user) == null;
    }

    public synchronized boolean update(User user) {
        return usersList.replace(user.getId(), user) == null;
    }

    public synchronized boolean delete(User user) {
        return usersList.remove(user.getId(), user);
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User one = usersList.get(fromId);
        User two = usersList.get(toId);
        if (one != null && two != null && one.getAmount() >= amount) {
            one.setAmount(usersList.get(fromId).getAmount() - amount);
            two.setAmount(usersList.get(toId).getAmount() + amount);
            return true;

        }
        return false;
    }

    public static void main(String[] args) {
        User one = new User(1, 100);
        User two = new User(2, 50);
        UserStorage storage = new UserStorage();
        storage.add(one);
        storage.add(two);
        storage.transfer(1, 2, 50);
        System.out.println(one.getAmount());
        System.out.println(two.getAmount());
    }
}
