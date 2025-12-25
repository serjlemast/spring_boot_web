package com.serjlemast1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class SingletonVsPrototypeApplication {

    @Bean
    public Map<Integer, User> dataSource(){
        return new ConcurrentHashMap<>();
    }

    @Bean
    public AtomicInteger idGenerator(){
        return new AtomicInteger();
    }

    public static void main(String[] args) {
        SpringApplication.run(SingletonVsPrototypeApplication.class, args);
    }

}
