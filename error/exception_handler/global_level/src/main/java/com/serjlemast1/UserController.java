package com.serjlemast1;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

   private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Collection<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        if (user.getEmail() == null) {
            throw new ControllerLevelException("Email is required");
        }

        if (user.getName() == null) {
            throw new ControllerLevelException("Name is required");
        }

        return ResponseEntity.ok(userService.create(user));
    }

    @Data
    public static class User {
        private Integer id;
        private String name;
        private String email;
    }
}
