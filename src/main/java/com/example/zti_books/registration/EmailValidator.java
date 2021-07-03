package com.example.zti_books.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
        return org.apache.commons.validator.routines.EmailValidator.getInstance().isValid(s);
    }
}
