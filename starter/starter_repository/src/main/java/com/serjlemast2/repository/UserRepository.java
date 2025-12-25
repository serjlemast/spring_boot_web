package com.serjlemast2.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserRepository {

    private final Map<Integer, UserEntity> users = new ConcurrentHashMap<>();

    private final AtomicInteger counter = new AtomicInteger();

    public Optional<UserEntity> findUserById(Integer id) {
        return Optional.ofNullable(users.get(id));
    }

    public UserEntity createUser(UserEntity user) {
        int id = counter.incrementAndGet();
        UserEntity entity = new UserEntity(id, user.name(), user.email());
        users.put(id, entity);
        return entity;
    }

    public List<UserEntity> findAllUsers() {
        return new ArrayList<>(users.values());
    }

    public void deleteUser(Integer id) {
        users.remove(id);
    }
}
