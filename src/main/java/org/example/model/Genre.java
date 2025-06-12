package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "Genres")
@Data
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Integer genreId;

    @NotBlank(message = "Genre name is required")
    @Size(max = 50, message = "Genre name cannot be longer than 50 characters")
    @Column(name = "genre_name", nullable = false)
    private String genreName;
}