package com.serjlemast1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@RestController
public class UserController {

    @GetMapping("/user")
    public ResponseEntity<String> getUser() {
        IntStream.range(0, 10).forEach(i -> {
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Working!");
        });
        return ResponseEntity.ok("Hello World");
    }
}