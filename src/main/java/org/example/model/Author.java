package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "Authors")
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Integer authorId;

    @NotBlank(message = "Author name is required")
    @Size(max = 100, message = "Author name cannot be longer than 100 characters")
    @Column(name = "author_name", nullable = false)
    private String authorName;

    @Column(name = "biography", columnDefinition = "TEXT") // Используем TEXT для больших биографий
    private String biography;
}