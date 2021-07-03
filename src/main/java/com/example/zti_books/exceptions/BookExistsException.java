package com.example.zti_books.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.I_AM_A_TEAPOT, reason = "Book with this title already exists")
public class BookExistsException extends Exception {
    public BookExistsException(String message) {
        super(message);
    }
}
