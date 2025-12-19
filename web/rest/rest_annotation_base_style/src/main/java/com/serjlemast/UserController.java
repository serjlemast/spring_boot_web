package com.serjlemast;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Map<Integer, User> users = new ConcurrentHashMap<>();

    private final AtomicInteger counter = new AtomicInteger(0);

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Integer id) {
        User user = users.get(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<Collection<User>> findAll() {
        return ResponseEntity.ok(users.values());
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        var id = counter.incrementAndGet();
        user.setId(id);
        users.put(id, user);
        return ResponseEntity.ok(user);
    }

    @Data
    public static class User {
        private Integer id;
        private String name;
    }

}
