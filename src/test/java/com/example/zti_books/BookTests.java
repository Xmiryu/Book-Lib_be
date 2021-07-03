package com.example.zti_books;

import com.example.zti_books.book.Book;
import com.example.zti_books.book.BookRepository;
import com.example.zti_books.book.BookService;
import com.example.zti_books.exceptions.BookExistsException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookTests {
    private Book test;
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;


    @BeforeAll
    void start() throws BookExistsException {
        this.test = new Book( "Test", "Test", 1111l);
    }

    @Test
    void BookExists() throws BookExistsException {
        int before = bookService.getBooks().size();
        bookService.addBook(test);
        int after = bookService.getBooks().size();

        Assertions.assertTrue(before == after-1);

    }

    @Test
    void throwsAddSameBookException(){
        Assertions.assertThrows(BookExistsException.class, ()->bookService.addBook(test));
    }

    @AfterAll
    void end(){
        bookRepository.deleteById(test.getId());
    }
}
