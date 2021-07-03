package com.example.zti_books.book;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {

    @Id
    private String id;
    private String title;
    private String author;
    private Long yearPublished;

    public Book(String title, String author, Long yearPublished) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
    }
}
