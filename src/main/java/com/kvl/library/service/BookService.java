package com.kvl.library.service;

import com.kvl.library.entity.Book;
import com.kvl.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book findBookById(final Long id) {
        return findById(id);
    }

    private Book findById(final Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book in not found"));
    }

    public void createBook(final Book book) {
        bookRepository.save(book);
    }

    public void deleteBook(final Long id) {
        final Book book = findById(id);
        bookRepository.deleteById(book.getId());
    }
}
