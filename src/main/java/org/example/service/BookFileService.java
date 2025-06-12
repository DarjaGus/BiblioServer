package org.example.service;

import org.example.model.BookFile;
import org.example.repository.BookFileRepository;
import org.example.config.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookFileService {

    private static final Logger logger = LoggerFactory.getLogger(org.example.service.BookFileService.class);

    private final BookFileRepository bookFileRepository;

    @Autowired
    public BookFileService(BookFileRepository bookFileRepository) {
        this.bookFileRepository = bookFileRepository;
    }

    public List<BookFile> getAllBookFile() {
        logger.info("Fetching all file");
        return bookFileRepository.findAll();
    }

    public BookFile getBookFileById(Integer id) {
        logger.info("Fetching file by id: {}", id);
        return bookFileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("file not found with id: " + id));
    }

    @Transactional
    public BookFile saveBookFile(BookFile bookFile) {
        logger.info("Saving file: {}", bookFile.getFilePath());
        return bookFileRepository.save(bookFile);
    }

    @Transactional
    public BookFile updateBookFile(Integer id, BookFile fileDetails) {
        logger.info("Updating file with id: {}", id);
        BookFile file = bookFileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("file not found with id: " + id));

        file.setFilePath(fileDetails.getFilePath());

        return bookFileRepository.save(file);
    }

    public void deleteBookFile(Integer id) {
        logger.info("Deleting file with id: {}", id);
        BookFile file = bookFileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("file not found with id: " + id));

        bookFileRepository.delete(file);
    }

    public List<BookFile> findByBookFileName(String fileName) {
        logger.info("Searching file by name: {}", fileName);
        return bookFileRepository.findByBookFileNameContainingIgnoreCase(fileName);
    }
}
