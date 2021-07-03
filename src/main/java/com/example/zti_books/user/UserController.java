package com.example.zti_books.user;

import com.example.zti_books.book.Book;
import com.example.zti_books.exceptions.BookNotExists;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RequestMapping("/pikachu")
@RestController
@Service
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping("/addRead/{bookId}")
    public void addReadBookRequest(@PathVariable String bookId) throws BookNotExists {
        System.out.println("HERE");
        this.userService.addReadBook(bookId);
    }

    @PutMapping("/addToRead/{bookId}")
    public void addToReadRequest(@PathVariable String bookId) throws BookNotExists{
        this.userService.addToReadBooks(bookId);
    }

    @DeleteMapping("/{bookId}")
    public void deleteToReadRequest(@PathVariable String bookId) throws BookNotExists{
        this.userService.deleteToReadBook(bookId);
    }

    @GetMapping(path = "/getRead")
    public Set<Book> getReadBooksRequest(){
        return this.userService.getReadBooks();
    }

    @GetMapping(path = "/getToRead")
    public Set<Book> getToReadBooksRequest(){
        return this.userService.getToReadBooks();
    }
}
