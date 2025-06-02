package org.example.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String locale;
    private String name;

    @OneToMany(mappedBy = "originalLanguage")
    private Set<Book> books;
}
