package com.example.zti_books.book;

import com.example.zti_books.exceptions.BookExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> getBooks() {
        return (List<Book>) this.bookRepository.findAll();
    }

    public void addBook(Book book) throws BookExistsException{
        boolean bookExists = this.bookRepository.findByTitleAndAuthor(book.getTitle(), book.getAuthor()).isPresent();
        if (bookExists){
            throw new BookExistsException("Book with this title and author already exists");
        }
        this.bookRepository.save(book);
    }
}
