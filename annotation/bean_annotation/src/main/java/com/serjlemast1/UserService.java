package com.serjlemast1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class UserService {

    private final Map<Integer, User> dataSource;

    private final AtomicInteger idGenerator;

    public List<User> findAll() {
        return new ArrayList<>(dataSource.values());
    }

    public User create(User user) {
        return dataSource.compute(idGenerator.incrementAndGet(), (newId, ignored) -> {
            user.setId(newId);
            return user;
        });
    }

    public User findById(Integer id) {
        return dataSource.get(id);
    }
}
