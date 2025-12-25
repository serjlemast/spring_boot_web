package com.serjlemast1;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
public class UserService {

    private final Map<Integer, UserController.User> users = new ConcurrentHashMap<>();

    private final AtomicInteger counter = new AtomicInteger(0);

    public UserController.User findById(Integer id) {
        return users.get(id);
    }

    public Collection<UserController.User> findAll() {
        return users.values();
    }

    public UserController.User create(UserController.User user) {
        return users.computeIfAbsent(counter.incrementAndGet(), key -> {
            user.setId(key);
            return user;
        });
    }
}
