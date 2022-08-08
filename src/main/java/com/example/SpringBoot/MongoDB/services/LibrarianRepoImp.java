package com.example.SpringBoot.MongoDB.services;

import com.example.SpringBoot.MongoDB.models.Librarian;
import com.example.SpringBoot.MongoDB.models.enums.Section;
import com.example.SpringBoot.MongoDB.repository.LibrarianRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@Profile("repo")
public class LibrarianRepoImp implements LibrarianService{

    private final LibrarianRepository librarianRepository;

    public LibrarianRepoImp(LibrarianRepository librarianRepository) {
        this.librarianRepository = librarianRepository;
    }

    @Override
    public Librarian addLibrarian(Librarian librarian) {
        return librarianRepository.save(librarian);
    }

    @Override
    public List<Librarian> getAllLibrarians(Section section) {
        if(section != null){
            return librarianRepository.findBySection(section);
        }
        List<Librarian> libs = new ArrayList<>();
        librarianRepository.findAll().forEach(libs::add);
        return libs;
    }

    @Override
    public Librarian getLibrarianById(String id) {
        Optional<Librarian> lib = librarianRepository.findById(id);
        return lib.orElse(null);
    }

    @Override
    public Librarian updateLibrarian(String id, Librarian librarian) {
        Optional<Librarian> libData = librarianRepository.findById(id);

        if(libData.isPresent()){
            Librarian _lib = libData.get();
            _lib.setFirstName(librarian.getFirstName());
            _lib.setLastName(librarian.getLastName());
            _lib.setAge(librarian.getAge());
            _lib.setSection(librarian.getSection());
            return librarianRepository.save(_lib);
        }
        return null;
    }

    @Override
    public void deleteLibrarian(String id) {
        librarianRepository.deleteById(id);
    }
}
