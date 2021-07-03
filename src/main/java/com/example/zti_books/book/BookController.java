package com.example.zti_books.book;

import com.example.zti_books.exceptions.BookExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/pikachu")
@RestController
@Service
@AllArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public List<Book> getAllBooks(){
        return this.bookService.getBooks();
    }

    @PostMapping
    public void addBook(@RequestBody Book b) throws BookExistsException {
        this.bookService.addBook(b);
    }

}
