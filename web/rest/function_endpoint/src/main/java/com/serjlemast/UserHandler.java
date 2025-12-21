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
    private final AtomicInteger idGenerator = new AtomicInteger();

    public ServerResponse getUserById(ServerRequest request) {
        String idStr = request.pathVariable("id");
        int userId = Integer.parseInt(idStr);
        User user = users.get(userId);

        if (user == null) {
            return ServerResponse.notFound().build();
        }

        return ServerResponse.ok().body(user);
    }

    public ServerResponse getAllUsers(ServerRequest request) {
        return ServerResponse.ok().body(users.values());
    }

    public ServerResponse createUser(ServerRequest request) throws ServletException, IOException {
        User user = request.body(User.class);
        int id = idGenerator.incrementAndGet();
        user.setId(id);
        users.put(id, user);
        return ServerResponse.ok().body(user);
    }
}
