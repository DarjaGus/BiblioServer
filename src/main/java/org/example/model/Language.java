package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "Languages")
@Data
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Integer languageId;

    @NotBlank(message = "Language name is required")
    @Size(max = 50, message = "Language name cannot be longer than 50 characters")
    @Column(name = "language_name", nullable = false)
    private String languageName;
}