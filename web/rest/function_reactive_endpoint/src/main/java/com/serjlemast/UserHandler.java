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
    private final AtomicInteger idGenerator = new AtomicInteger();

    public Mono<ServerResponse> getUserById(ServerRequest request) {
        return Mono.fromSupplier(() -> request.pathVariable("id"))
                .map(Integer::parseInt)
                .map(users::get)
                .flatMap(user ->
                        user != null
                                ? ServerResponse.ok().bodyValue(user)
                                : ServerResponse.notFound().build()
                );
    }

    public Mono<ServerResponse> getAllUsers(ServerRequest request) {
        return ServerResponse.ok()
                .bodyValue(users.values());
    }

    public Mono<ServerResponse> createUser(ServerRequest request) {
        return request.bodyToMono(User.class)
                .map(this::assignId)
                .doOnNext(user -> users.put(user.getId(), user))
                .flatMap(user -> ServerResponse.ok().bodyValue(user));
    }

    private User assignId(User user) {
        user.setId(idGenerator.incrementAndGet());
        return user;
    }
}
