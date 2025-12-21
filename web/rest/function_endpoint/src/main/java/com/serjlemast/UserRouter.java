package com.serjlemast;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> routerUser(UserHandler handler) {
        return route()
                .GET("/users/{id}", handler::getUserById)
                .GET("/users", handler::getAllUsers)
                .POST("/users", handler::createUser)
                .build();
    }
}
