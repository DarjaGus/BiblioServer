package org.example.entity;

import javax.persistence.*;
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
