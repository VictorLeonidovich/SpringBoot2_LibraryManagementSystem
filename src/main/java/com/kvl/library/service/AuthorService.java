package com.kvl.library.service;

import com.kvl.library.entity.Author;
import com.kvl.library.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> findAllAuthors() {
        log.info("Fetching all authors from the database");
        return authorRepository.findAll();
    }

    public Author findAuthorById(final Long id) {
        Author author = findById(id);
        log.info("Fetched author '{}' by id '{}' from the database", author, id);
        return author;
    }

    private Author findById(final Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author in not found"));
    }

    public void createAuthor(final Author author) {
        log.info("Saving author '{}' to the database", author);
        authorRepository.save(author);
    }

    public void updateAuthor(final Author author) {
        log.info("Updating author '{}' in the database", author);
        authorRepository.save(author);
    }

    public void deleteAuthor(final Long id) {
        final Author author = findById(id);
        log.info("Deleting author '{}' by id '{}' from the database", author, id);
        authorRepository.deleteById(author.getId());
    }
}
