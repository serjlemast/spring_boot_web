package com.serjlemast1;

import lombok.AllArgsConstructor;
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

    private final Map<Integer, Userv1> users = new ConcurrentHashMap<>();

    private final AtomicInteger counter = new AtomicInteger(0);

    @GetMapping("/{id}")
    public ResponseEntity<Userv1> findUserById(@PathVariable Integer id) {
        Userv1 user = users.get(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<Collection<Userv1>> findAll() {
        return ResponseEntity.ok(users.values());
    }

    @GetMapping(version = "v2")
    public ResponseEntity<Collection<Userv2>> findAllv2() {
        return ResponseEntity.ok(users.values()
                .stream()
                .map(v1 -> new Userv2(v1.id, v1.name, "v2"))
                .toList());
    }

    @PostMapping
    public ResponseEntity<Userv1> save(@RequestBody Userv1 user) {
        var id = counter.incrementAndGet();
        user.setId(id);
        users.put(id, user);
        return ResponseEntity.ok(user);
    }

    @Data
    public static class Userv1 {
        private Integer id;
        private String name;
    }

    @Data
    @AllArgsConstructor
    public static class Userv2 {
        private Integer id;
        private String lastName;
        private String firstName;
    }

}
