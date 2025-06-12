package org.example.api;

import jakarta.validation.Valid;
import org.example.model.BookFile;
import org.example.service.BookFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    private final BookFileService bookFileService;

    @Autowired
    public FileController(BookFileService bookFileService) {
        this.bookFileService = bookFileService;
    }

    @GetMapping
    public ResponseEntity<List<BookFile>> getAllBookFile() {
        logger.info("GET request to fetch all files");
        List<BookFile> bookFiles = bookFileService.getAllBookFile();
        return new ResponseEntity<>(bookFiles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookFile> getBookFileById(@PathVariable Integer id) {
        logger.info("GET request to fetch file with id: {}", id);
        BookFile bookFile = bookFileService.getBookFileById(id);
        return new ResponseEntity<>(bookFile, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookFile> createBookFile(@Valid @RequestBody BookFile bookFile) {
        logger.info("POST request to create genre: {}", bookFile.getFilePath());
        BookFile savedBookFile = bookFileService.saveBookFile(bookFile);
        return new ResponseEntity<>(savedBookFile, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookFile> updateBookFile(@PathVariable Integer id, @Valid @RequestBody BookFile bookFileDetails) {
        logger.info("PUT request to update file with id: {}", id);
        BookFile updatedBookFile = bookFileService.updateBookFile(id, bookFileDetails);
        return new ResponseEntity<>(updatedBookFile, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookFile(@PathVariable Integer id) {
        logger.info("DELETE request to delete file with id: {}", id);
        bookFileService.deleteBookFile(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookFile>> searchBookFile(@RequestParam String name) {
        logger.info("GET request to search file by name: {}", name);
        List<BookFile> bookFiles = bookFileService.findByBookFileName(name);
        return new ResponseEntity<>(bookFiles, HttpStatus.OK);
    }
}