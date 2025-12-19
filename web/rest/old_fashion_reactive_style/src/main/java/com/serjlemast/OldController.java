package com.serjlemast;

import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

@RestController
@RequestMapping("/users")
public class OldController {

    private final Random random = new Random();
    private final String SYMBOLS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(SYMBOLS.charAt(random.nextInt(SYMBOLS.length())));
        }
        return sb.toString();
    }

    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<StreamingResponseBody> streamRandom() {

        StreamingResponseBody stream = new StreamingResponseBody() {
            @Override
            public void writeTo(OutputStream out) throws IOException {
                int count = 5 + random.nextInt(3); // 5-7 strings

                for (int i = 0; i < count; i++) {
                    String value = generateRandomString(8) + "\n";
                    out.write(value.getBytes());
                    out.flush(); // send immediately
                    try {
                        TimeUnit.MILLISECONDS.sleep(300); // delay 5 sec
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }
        };

        return ResponseEntity.ok(stream);
    }
}
