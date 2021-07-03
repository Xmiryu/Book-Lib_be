package com.example.zti_books.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.I_AM_A_TEAPOT, reason = "Invalid email or password")
public class InvalidCredentials extends Exception{

    public InvalidCredentials(String invalid_email_or_password) {
    }
}
