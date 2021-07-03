package com.example.zti_books.registration;

import com.example.zti_books.exceptions.InvalidEmailException;
import com.example.zti_books.exceptions.UserExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pikachu/register")
@Service
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) throws UserExistsException, InvalidEmailException {
        return registrationService.register(request);
    }

}
