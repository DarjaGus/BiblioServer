package org.example.service;

import org.example.config.ResourceNotFoundException;
import org.example.model.Book;
import org.example.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        logger.info("Fetching all books");
        return bookRepository.findAll();
    }

    public Book getBookById(Integer id) {
        logger.info("Fetching book by id: {}", id);
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }

    @Transactional
    public Book saveBook(Book book) {
        logger.info("Saving book: {}", book.getTitle());
        return bookRepository.save(book);
    }

    @Transactional
    public Book updateBook(Integer id, Book bookDetails) {
        logger.info("Updating book with id: {}", id);
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        book.setTitle(bookDetails.getTitle());
        book.setDescription(bookDetails.getDescription());
        book.setLanguage(bookDetails.getLanguage());
        book.setSeries(bookDetails.getSeries());
        book.setSeriesNum(bookDetails.getSeriesNum());
        book.setPublicationYear(bookDetails.getPublicationYear());
        book.setAuthors(bookDetails.getAuthors());
        book.setGenres(bookDetails.getGenres());

        return bookRepository.save(book);
    }

    public void deleteBook(Integer id) {
        logger.info("Deleting book with id: {}", id);
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        bookRepository.delete(book);
    }

    public List<Book> findByTitle(String title) {
        logger.info("Searching books by title: {}", title);
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Book> findByAuthorName(String authorName) {
        logger.info("Searching books by author: {}", authorName);
        return bookRepository.findByAuthorName(authorName);
    }

    public List<Book> findByGenreName(String genreName) {
        logger.info("Searching books by genre: {}", genreName);
        return bookRepository.findByGenreName(genreName);
    }
}