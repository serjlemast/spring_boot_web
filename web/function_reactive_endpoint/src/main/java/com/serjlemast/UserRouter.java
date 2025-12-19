package com.serjlemast;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(UserHandler handler) {
        return RouterFunctions.route()
                .GET("/users/{id}", handler::findUserById)
                .GET("/users", handler::findAll)
                .POST("/users", handler::create)
                .build();
    }
}
