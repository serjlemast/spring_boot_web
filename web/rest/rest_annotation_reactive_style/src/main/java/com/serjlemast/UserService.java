package com.serjlemast;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserService {

    private final Map<Integer, User> users = new ConcurrentHashMap<>();

    private final AtomicInteger counter = new AtomicInteger(0);

    public Mono<User> findUserById(int id) {
        return Mono.just(users.get(id));
    }

    public Flux<User> findAll() {
        return Flux.fromIterable(users.values())
                .delayElements(Duration.ofMillis(1000));
    }

    public Mono<User> create(User user) {
        return Mono.just(user)
                .map(u -> users.computeIfAbsent(counter.incrementAndGet(), (k) -> {
                    u.setId(k);
                    return u;
                }));
    }
}
