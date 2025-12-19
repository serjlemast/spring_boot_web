package com.serjlemast;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Mono<User>> findUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Flux<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/v2")
    public Mono<ResponseEntity<Flux<User>>> findAll2() {
        return Mono.just(ResponseEntity.ok(userService.findAll()));
    }

    @PostMapping
    public ResponseEntity<Mono<User>> save(@RequestBody User user) {
        return ResponseEntity.ok(userService.create(user));
    }
}
