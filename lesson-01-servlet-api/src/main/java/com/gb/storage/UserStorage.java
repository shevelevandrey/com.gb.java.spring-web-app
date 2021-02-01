package com.gb.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UserStorage {
    private Map<Long, User> users = new ConcurrentHashMap<>();
    private AtomicLong identity = new AtomicLong(0);


    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public User findById(long id) {
        return users.get(id);
    }

    public void add(User user) {
        user.setId(identity.incrementAndGet());
        users.put(user.getId(), user);
    }

    public void update(User user) {
        users.put(user.getId(), user);
    }

    public void delete(long id) {
        users.remove(id);
    }
}
