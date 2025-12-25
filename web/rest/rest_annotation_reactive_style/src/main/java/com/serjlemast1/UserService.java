package com.serjlemast1;

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
    private final AtomicInteger counter = new AtomicInteger();

    public Mono<User> findUserById(int id) {
        return Mono.defer(() -> Mono.justOrEmpty(users.get(id)));
    }

    public Flux<User> findAll() {
        return Flux.defer(() -> Flux.fromIterable(users.values()))
                .delayElements(Duration.ofSeconds(1)); // optional/demo-only
    }

    public Mono<User> create(User user) {
        return Mono.defer(() -> {
            int id = counter.incrementAndGet();
            user.setId(id);
            users.put(id, user);
            return Mono.just(user);
        });
    }
}
