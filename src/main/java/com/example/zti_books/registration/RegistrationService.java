package com.example.zti_books.registration;

import com.example.zti_books.exceptions.InvalidEmailException;
import com.example.zti_books.exceptions.UserExistsException;
import com.example.zti_books.user.User;
import com.example.zti_books.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final EmailValidator emailValidator;
    private final UserService userService;

    public String register(RegistrationRequest request) throws UserExistsException, InvalidEmailException {
        boolean isEmailValid = emailValidator.test(request.getEmail());
        if (!isEmailValid) {
            throw new InvalidEmailException("Email is not valid");
        }
        return userService.signUp(new User(request.getName(), request.getSurname(), request.getEmail(), request.getPassword()));

    }

}
