package org.example.api;

import org.example.model.Book;
import org.example.model.BookFiles;
import org.example.service.BookFileService;
import org.example.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private BookFileService bookFileService;

    @Autowired
    private StorageService storageService;

    @GetMapping
    public List<BookFiles> getAllBookFiles() {
        return bookFileService.findAllBookFiles();
    }

    @GetMapping("/{id}")
    public BookFiles getBookFileById(@PathVariable Long id) {
        return bookFileService.findBookFileById(id);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadBookFile(@RequestParam("file") MultipartFile file, @RequestParam("bookId") Long bookId) {
        try {
            String filePath = storageService.storeFile(file);
            BookFiles bookFile = new BookFiles();
            bookFile.setFileType(file.getContentType());
            bookFile.setFilePath(filePath);
            bookFile.setBook(new Book());
            bookFile.getBook().setId(bookId);
            bookFileService.saveBookFile(bookFile);
            return ResponseEntity.ok("File uploaded successfully: " + filePath);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadBookFile(@PathVariable Long id) {
        try {
            BookFiles bookFile = bookFileService.findBookFileById(id);
            byte[] fileData = storageService.loadFile(bookFile.getFilePath());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + bookFile.getFilePath() + "\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(fileData);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteBookFile(@PathVariable Long id) {
        bookFileService.deleteBookFile(id);
    }
}