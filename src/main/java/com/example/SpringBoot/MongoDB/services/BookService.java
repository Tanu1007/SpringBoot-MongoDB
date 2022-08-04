package com.example.SpringBoot.MongoDB.services;

import com.example.SpringBoot.MongoDB.models.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    List<Book> getAllBooks(String bookName,String authorName);

    Book getBookById(String id);
    Book addBook(Book book);

    Book updateBook(String id,Book book);

    void deleteBook(String id);

}
