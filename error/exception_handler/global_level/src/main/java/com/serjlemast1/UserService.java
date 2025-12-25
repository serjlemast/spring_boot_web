package com.serjlemast1;

import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
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

    public UserController.@Nullable User findById(Integer id) {
        return users.get(id);
    }

    public @Nullable Collection<UserController.User> findAll() {
        return users.values();
    }

    public UserController.@Nullable User create(UserController.User user) {
        if(!user.getEmail().contains("@")) {
            throw new ServiceLevelException("Business require '@' symbol");
        }

        return users.computeIfAbsent(counter.incrementAndGet(), key -> {
            user.setId(key);
            return user;
        });
    }
}
