package com.example.zti_books.login;

import com.example.zti_books.exceptions.InvalidCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pikachu/login")
@Service
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping()
    public String login(@RequestBody LoginRequest loginRequest) throws InvalidCredentials {
        return loginService.login(loginRequest);
    }

}
