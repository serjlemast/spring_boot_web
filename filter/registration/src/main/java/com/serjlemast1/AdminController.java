package com.serjlemast1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public ResponseEntity<String> findAll() {
        return ResponseEntity.ok("Admin");
    }

}
