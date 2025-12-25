package com.serjlemast1;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

        if (user.getEmail() == null) {
            throw new MyException("Email is required");
        }

        if (user.getName() == null) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Name is required", new RuntimeException("blablabal"));
        }

        if(!user.getEmail().contains("@")) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Business require '@' symbol");
        }

        return users.computeIfAbsent(counter.incrementAndGet(), key -> {
            user.setId(key);
            return user;
        });
    }
}
