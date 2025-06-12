package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.model.Book;

@Entity
@Table(name = "Book_Files")
@Data
public class BookFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Integer fileId;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @NotBlank(message = "File type is required")
    @Size(max = 50, message = "File type cannot be longer than 50 characters")
    @Column(name = "file_type", nullable = false)
    private String fileType;

    @NotBlank(message = "File path is required")
    @Size(max = 255, message = "File path cannot be longer than 255 characters")
    @Column(name = "file_path", nullable = false)
    private String filePath;
}