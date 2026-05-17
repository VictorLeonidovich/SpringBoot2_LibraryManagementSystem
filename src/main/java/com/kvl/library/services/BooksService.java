package com.kvl.library.services;

import com.kvl.library.entity.Book;
import com.kvl.library.repositories.BooksRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public Page<Book> findWithPaginationAndSorting(int page, int booksPerPage, String sortBy, String order) {
        Sort sort = order.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        return booksRepository.findAllWithAuthors(PageRequest.of(page, booksPerPage, sort));
    }

    public Book findBookById(final Long id) {
        Book book = findById(id);
        log.info("Fetched book '{}' by id '{}' from the database", book.getName(), id);
        return book;
    }

    private Book findById(final Long id) {
        return booksRepository.findById(id).orElseThrow(() -> new RuntimeException("Book is not found"));
    }

    @Transactional
    public void createBook(final Book book) {
        log.info("Saving book '{}' to the database", book.getName());
        booksRepository.save(book);
    }

    @Transactional
    public void updateBook(final Book book) {
        log.info("Updating book '{}' in the database", book.getName());

        if (book.getId() != null) {
            Book managedBook = booksRepository.findById(book.getId())
                    .orElseThrow(() -> new RuntimeException("Cannot update book: entity does not exist"));

            managedBook.setName(book.getName());
            managedBook.setIsbn(book.getIsbn());
            managedBook.setDescription(book.getDescription());

            // Безопасно обновляем связи Many-to-Many (чтобы Hibernate не ругался на дубликаты)
            if (book.getAuthors() != null) {
                managedBook.getAuthors().clear();
                managedBook.getAuthors().addAll(book.getAuthors());
            }
            if (book.getCategories() != null) {
                managedBook.getCategories().clear();
                managedBook.getCategories().addAll(book.getCategories());
            }
            if (book.getPublishers() != null) {
                managedBook.getPublishers().clear();
                managedBook.getPublishers().addAll(book.getPublishers());
            }

        } else {
            throw new RuntimeException("Cannot update book: ID is missing");
        }
    }

    @Transactional
    public void deleteBook(final Long id) {
        final Book book = findById(id);
        log.info("Deleting book '{}' by id '{}' from the database", book.getName(), id);

        booksRepository.delete(book);
    }
}
