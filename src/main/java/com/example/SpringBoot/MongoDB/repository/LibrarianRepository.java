package com.example.SpringBoot.MongoDB.repository;

import com.example.SpringBoot.MongoDB.models.Librarian;
import com.example.SpringBoot.MongoDB.models.enums.Section;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibrarianRepository extends ElasticsearchRepository<Librarian, String> {

    List<Librarian> findByFirstName(String firstName);
    List<Librarian> findBySection(Section section);

}
