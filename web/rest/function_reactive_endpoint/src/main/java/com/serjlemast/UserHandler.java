package com.serjlemast;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class UserHandler {

    private final Map<Integer, User> users = new ConcurrentHashMap<>();

    private final AtomicInteger counter = new AtomicInteger(0);

    public Mono<ServerResponse> findUserById(ServerRequest request) {
        return Mono.just(request.pathVariable("id"))
                .map(Integer::valueOf)
                .map(users::get)
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok().bodyValue(users.values());
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(User.class)
                .map(user -> {
                    user.setId(counter.incrementAndGet());
                    return user;
                })
                .map(user -> {
                    users.put(user.getId(), user);
                    return user;
                })
                .flatMap(ServerResponse.ok()::bodyValue);
    }
}
