package com.example.SpringBoot.MongoDB.repository;

import com.example.SpringBoot.MongoDB.models.Book;
import com.example.SpringBoot.MongoDB.models.enums.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book,String> {

    Optional<Book> findById(String ID);
    List<Book> findByBookName(String bookName);
    List<Book> findByAuthorName(String authorName);
    List<Book> findByGenre(Genre genre);

}
