package com.example.SpringBoot.MongoDB.services;

import com.example.SpringBoot.MongoDB.models.Librarian;
import com.example.SpringBoot.MongoDB.models.enums.Genre;
import com.example.SpringBoot.MongoDB.models.enums.Section;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LibrarianService {
    Librarian addLibrarian(Librarian librarian);

    List<Librarian> getAllLibrarians(Section section);

    Librarian getLibrarianById(String id);

    Librarian updateLibrarian(String id,Librarian librarian);

    void deleteLibrarian(String id);

}
