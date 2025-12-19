package com.serjlemast;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

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

    @ExceptionHandler(ControllerLevelException.class)
    public ErrorResponse errorControllerHandler(Exception e) {
        return ErrorResponse.builder(e,HttpStatus.BAD_REQUEST, "bad message").build();
    }

    @ExceptionHandler(ServiceLevelException.class)
    public ErrorResponse errorServiceHandler(Exception e) {
        return ErrorResponse.builder(e,HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS, "illegal person").build();
    }

}
