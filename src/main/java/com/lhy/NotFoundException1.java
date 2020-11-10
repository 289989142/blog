package com.lhy;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException1 extends RuntimeException {
    public NotFoundException1() {
    }

    public NotFoundException1(String message) {
        super(message);
    }

    public NotFoundException1(String message, Throwable cause) {
        super(message, cause);
    }
}
