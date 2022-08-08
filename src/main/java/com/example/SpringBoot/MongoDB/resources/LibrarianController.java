package com.example.SpringBoot.MongoDB.resources;

import com.example.SpringBoot.MongoDB.models.Librarian;
import com.example.SpringBoot.MongoDB.models.enums.Section;
import com.example.SpringBoot.MongoDB.services.LibrarianService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@Slf4j
public class LibrarianController {

    private final LibrarianService librarianService;

    public LibrarianController(LibrarianService librarianService) {
        this.librarianService = librarianService;
    }

    @GetMapping("/librarian")
    public ResponseEntity<List<Librarian>> getAllLibrarians(@PathParam("section")Section section){
        try{
            List<Librarian> libs;
            if(section != null){
                libs = librarianService.getAllLibrarians(section);
            }else{
                libs = librarianService.getAllLibrarians(null);
            }
            if(libs==null || libs.isEmpty()){
                log.info("No Librarians found in this section");
                return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
            }

            log.info("{} librarians found", libs.size());
            return new ResponseEntity<>(libs,HttpStatus.OK);
        }catch (Exception e){
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/librarian/{id}")
    public ResponseEntity<Librarian> getLibrarianById(@PathVariable("id") String id){
        try {
            Librarian lib = librarianService.getLibrarianById(id);
            if(lib == null){
                log.error("Librarian not found with id: {}", id);
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(lib, HttpStatus.OK);
        } catch (Exception e){
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addLibrarian")
    public ResponseEntity<Librarian> addLibrarian(@RequestBody Librarian lib){
        //repository.save(book);
        try{
            if(lib==null){
                log.warn("Librarian not added.");
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            Librarian b = librarianService.addLibrarian(lib);
            log.info("Librarian added Successfully: {}",b);
            return new ResponseEntity<>(b,HttpStatus.CREATED);
        }catch (Exception e){
            log.error(e.toString());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/updateLibrarian/{id}")
    public ResponseEntity<Librarian> updateBook(@PathVariable String id, @RequestBody Librarian lib){
        try {
            Librarian b = librarianService.updateLibrarian(id, lib);

            if(b == null){
                log.error("Unable to update librarian with id: {}", id);
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            log.info("Updated librarian with id: {}", id);
            return new ResponseEntity<>(b, HttpStatus.OK);
        } catch (Exception e){
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/librarian/{id}")
    public ResponseEntity<HttpStatus> deleteCrew(@PathVariable("id") String id){
        try {
            librarianService.deleteLibrarian(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            log.error(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
