package com.example.SpringBoot.MongoDB.services;

import com.example.SpringBoot.MongoDB.models.Book;
import com.example.SpringBoot.MongoDB.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Profile("repo")
public class BookRepoImp implements BookService {

    private final BookRepository bookRepository;

    public BookRepoImp(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks(String bookName, String authorName) {
        if(bookName != null){
            return bookRepository.findByBookName(bookName);
        }
        if (authorName != null){
            return bookRepository.findByAuthorName(authorName);
        }
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(String id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(String id, Book book) {
        Optional<Book> bookData = bookRepository.findById(id);
        if(bookData.isPresent()){
            Book b = bookData.get();
            b.setBookName(book.getBookName());
            b.setAuthorName(book.getAuthorName());
            return bookRepository.save(b);
        }
        return null;
    }

    @Override
    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }
}
