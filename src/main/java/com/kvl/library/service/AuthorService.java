package com.kvl.library.service;

import com.kvl.library.entity.Author;
import com.kvl.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public Author findAuthorById(final Long id) {
        return findById(id);
    }

    private Author findById(final Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author in not found"));
    }

    public void createAuthor(final Author author) {
        authorRepository.save(author);
    }

    public void updateAuthor(final Author author) {
        authorRepository.save(author);
    }

    public void deleteAuthor(final Long id) {
        final Author author = findById(id);
        authorRepository.deleteById(author.getId());
    }
}
