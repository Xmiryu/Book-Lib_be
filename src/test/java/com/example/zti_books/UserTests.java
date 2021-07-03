package com.example.zti_books;

import com.example.zti_books.exceptions.InvalidCredentials;
import com.example.zti_books.exceptions.UserExistsException;
import com.example.zti_books.login.LoginRequest;
import com.example.zti_books.login.LoginService;
import com.example.zti_books.user.User;
import com.example.zti_books.user.UserRepository;
import com.example.zti_books.user.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserTests {
    private User test;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginService loginService;


    @BeforeAll
    void start() throws UserExistsException {
        this.test = new User("Test", "Test", "test@test.com", "test");
        this.userService.signUp(this.test);
    }

    @Test
    void login() throws InvalidCredentials {
        Assertions.assertTrue( loginService.login(new LoginRequest(this.test.getEmail(), "test")) instanceof String );
    }
    @Test
    void invalidCredence() {
        Assertions.assertThrows(InvalidCredentials.class, () -> loginService.login(new LoginRequest(this.test.getEmail(), "wrong one")));
    }

    @Test
    void throwUserExistsException() {
        Assertions.assertThrows(UserExistsException.class, () -> userService.signUp(new User("Test", "Test", "test@test.com", "test")));
    }


    @AfterAll
    void end(){
        userRepository.deleteById(test.getId());
    }
}
