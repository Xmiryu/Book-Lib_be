package com.example.zti_books.login;

import com.example.zti_books.exceptions.InvalidCredentials;
import com.example.zti_books.security.jwt.JwtTokenUtil;
import com.example.zti_books.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    public String login(LoginRequest loginRequest) throws InvalidCredentials {

        userService.checkCredentials(loginRequest.getEmail(), loginRequest.getPassword());
        return jwtTokenUtil.generateToken(loginRequest.getEmail());
    }
}
