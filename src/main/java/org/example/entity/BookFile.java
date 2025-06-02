package org.example.entity;

import javax.persistence.*;

@Entity
public class BookFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    private String filePath;
    private String fileType;
}
