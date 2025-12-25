package com.serjlemast1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "!?!?!")
public class MyException extends RuntimeException {
    public MyException(String message) {
        super(message);
    }
}
