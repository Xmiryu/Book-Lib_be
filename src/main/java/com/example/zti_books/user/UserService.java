package com.example.zti_books.user;

import com.example.zti_books.book.Book;
import com.example.zti_books.book.BookRepository;
import com.example.zti_books.exceptions.BookNotExists;
import com.example.zti_books.exceptions.InvalidCredentials;
import com.example.zti_books.exceptions.UserExistsException;
import com.example.zti_books.exceptions.UserNotExists;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
     User details = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with email: " + email + " does not exists!"));
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with email: " + email + " does not exists!"));
    }

    public String signUp(User user) throws UserExistsException {
        boolean userExists = this.userRepository.findByEmail(user.getEmail()).isPresent();

        if (userExists) {
            throw new UserExistsException("User with email " + user.getEmail() + "already exists");
        }

        String encodedPassword = this.bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        this.userRepository.save(user);

        return user.getEmail();
    }

    public Boolean checkCredentials(String email, String password) throws InvalidCredentials {
        Optional<User> usr = userRepository.findByEmail(email);
        if (usr.isEmpty() || !bCryptPasswordEncoder.matches(password, usr.get().getPassword()))
            throw new InvalidCredentials("Invalid email or password");
        return true;
    }

    private User getContextUser() throws UserNotExists {

        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return this.userRepository.findByEmail(email).orElseThrow(UserNotExists::new);
    }

    public void addReadBook(String bookId) throws BookNotExists, UserNotExists {
        Book book = this.bookRepository.findById(bookId).orElseThrow(BookNotExists::new);
        User u = getContextUser();
        Set<Book> read =  u.getReadBooks();
        if (read == null)
            read = new HashSet<>();

        read.add(book);
        u.setReadBooks(read);
        this.userRepository.save(u);
    }

    public void addToReadBooks(String bookId) throws BookNotExists, UserNotExists {
        Book book = this.bookRepository.findById(bookId).orElseThrow(BookNotExists::new);
        User u = getContextUser();
        Set<Book> toRead =  u.getToRead();
        if (toRead == null)
            toRead = new HashSet<>();
        toRead.add(book);
        u.setToRead(toRead);
        this.userRepository.save(u);

    }

    public void deleteToReadBook(String bookId) throws BookNotExists, UserNotExists {
        Book book = this.bookRepository.findById(bookId).orElseThrow(BookNotExists::new);
        User u = getContextUser();
        Set<Book> toRead =  u.getToRead();
        toRead.removeIf(bk -> bk.getId().equals(book.getId()));
        u.setToRead(toRead);
        this.userRepository.save(u);
    }

    public Set<Book> getReadBooks() throws UserNotExists {
        User u = getContextUser();
        return u.getReadBooks();
    }

    public Set<Book> getToReadBooks() throws UserNotExists {
        User u = getContextUser();
        return u.getToRead();
    }

}
