package org.example.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @OneToMany(mappedBy = "series")
    private Set<Book> books;
}
