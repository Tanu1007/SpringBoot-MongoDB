package com.example.SpringBoot.MongoDB.models;

import com.example.SpringBoot.MongoDB.models.enums.Section;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


@Getter
@Setter
@ToString
@NoArgsConstructor
@Document(indexName = "librarianidx")
public class Librarian {
    @Id
    private String id;
    @Field(type = FieldType.Text, name = "firstName")
    private String firstName;
    @Field(type = FieldType.Text, name = "lastName")
    private String lastName;
    @Field(type = FieldType.Integer, name = "age")
    private int age;

    private Section section;

    public Librarian(String firstName, String lastName, int age, Section section) {
        //this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.section = section;
    }
}
