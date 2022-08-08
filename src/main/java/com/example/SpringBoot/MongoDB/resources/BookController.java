package com.example.SpringBoot.MongoDB.resources;

import com.example.SpringBoot.MongoDB.models.Book;
import com.example.SpringBoot.MongoDB.models.enums.Genre;
import com.example.SpringBoot.MongoDB.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

//to commint changes use command+K

@RestController
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooksList(@PathParam("bookName") String bookName, @PathParam("authorName") String authorName, @PathParam("genre") Genre genre){
        try{

            List<Book> book;
            if(bookName !=null){
                book = bookService.getAllBooks(bookName,null,null);
            }
            else if(authorName != null){
                book = bookService.getAllBooks(null,authorName,null);
            }else if(genre != null){
                book = bookService.getAllBooks(null,null,genre);
            }else{
                book = bookService.getAllBooks(null,null,null);
            }

            if(book==null || book.isEmpty()){
                log.info("Book not Found");
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            log.info("{} book found",book.size());
            return new ResponseEntity<>(book, HttpStatus.OK);
        }catch (Exception e){
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") String id){
        try {
            Book book;
            if(id!=null){
                book = bookService.getBookById(id);
            }else{
                log.error("Unable to find book with id: {}", id);
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            //Book book = bookService.getBookById(id);
            //log.info("{} book found",book.getBookName());
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (Exception e){
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        //repository.save(book);
        try{
            if(book==null){
                log.warn("Book not added.");
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            Book b = bookService.addBook(book);
            log.info("Book added Successfully: {}",b);
            return new ResponseEntity<>(b,HttpStatus.CREATED);
        }catch (Exception e){
            log.error(e.toString());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/updateBook/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable String id, @RequestBody Book book){
        try {
            Book b = bookService.updateBook(id, book);

            if(b == null){
                log.error("Unable to update book with id: {}", id);
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            log.info("Updated book with id: {}", id);
            return new ResponseEntity<>(b, HttpStatus.OK);
        } catch (Exception e){
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<HttpStatus> deleteCrew(@PathVariable("id") String id){
        try {
            bookService.deleteBook(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            log.error(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
