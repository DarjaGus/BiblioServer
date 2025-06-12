package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "Series")
@Data
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "series_id")
    private Integer seriesId;

    @NotBlank(message = "Series name is required")
    @Size(max = 100, message = "Series name cannot be longer than 100 characters")
    @Column(name = "series_name", nullable = false)
    private String seriesName;
}