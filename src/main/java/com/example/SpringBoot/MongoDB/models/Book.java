package com.example.SpringBoot.MongoDB.models;

import com.example.SpringBoot.MongoDB.models.enums.Genre;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString

@Document(collection = "Book")
public class Book {

    @Id
    private String id;
    private String bookName;
    private String authorName;

    private Genre genre;

    public Book(String bookName, String authorName,Genre genre) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.genre = genre;
    }
}
