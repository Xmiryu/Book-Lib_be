package com.example.zti_books.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "User with this email already exists")
public class UserExistsException extends Exception {
    public UserExistsException(String message) {
        super(message);
    }
}
