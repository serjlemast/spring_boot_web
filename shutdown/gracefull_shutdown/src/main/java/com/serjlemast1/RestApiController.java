package com.serjlemast1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {

    @GetMapping
    public String helloWorld() {
        return "Hello World";
    }
}
