package com.example.zti_books.book;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {

    Optional<Object> findByTitle(String title);

    Optional<Book> findByTitleAndAuthor(String title, String author);
}