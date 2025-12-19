package com.serjlemast;

import jakarta.servlet.ServletException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class UserHandler {

    private final Map<Integer, User> users = new ConcurrentHashMap<>();

    private final AtomicInteger counter = new AtomicInteger(0);

    public ServerResponse findUserById(ServerRequest request) {
        String id = request.pathVariable("id");
        Integer userId = Integer.parseInt(id);
        User user = users.get(userId);
        return ServerResponse.ok().body(user);
    }

    public ServerResponse findAll(ServerRequest serverRequest) {
        return ServerResponse.ok().body(users.values());
    }

    public ServerResponse create(ServerRequest serverRequest) throws ServletException, IOException {
        User user = serverRequest.body(User.class);
        int id = counter.incrementAndGet();
        user.setId(id);
        users.put(id, user);
        return ServerResponse.ok().body(user);
    }
}
