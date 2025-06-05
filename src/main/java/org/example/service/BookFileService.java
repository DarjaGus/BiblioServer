package org.example.service;

import org.example.model.BookFiles;
import org.example.repository.BookFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookFileService {
    @Autowired
    private BookFileRepository bookFileRepository;

    public List<BookFiles> findAllBookFiles() {
        return bookFileRepository.findAll();
    }

    public BookFiles findBookFileById(Long id) {
        return bookFileRepository.findById(id).orElse(null);
    }

    public BookFiles saveBookFile(BookFiles bookFile) {
        return bookFileRepository.save(bookFile);
    }

    public void deleteBookFile(Long id) {
        bookFileRepository.deleteById(id);
    }
}
