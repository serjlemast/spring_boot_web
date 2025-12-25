package com.serjlemast1;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
            throw new RuntimeException("Email is required");
        }

        if (user.getName() == null) {
            throw new RuntimeException("Name is required");
        }

        return ResponseEntity.ok(userService.create(user));
    }

    @Data
    public static class User {
        private Integer id;
        private String name;
        private String email;
    }

    //https://www.rfc-editor.org/rfc/rfc9457.html
    @ExceptionHandler(RuntimeException.class)
    public ProblemDetail errorControllerHandler(Exception e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_GATEWAY);
        problemDetail.setTitle("Bad Request");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setType(URI.create("www.google.com"));
        return problemDetail;
    }
}
