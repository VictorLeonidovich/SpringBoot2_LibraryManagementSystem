package com.kvl.library.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name = "isbn", length = 50, nullable = false, unique = true)
    private String isbn;
    @Column(name = "description", length = 250, nullable = false)
    private String description;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "books_authors", joinColumns = {@JoinColumn(name = "book_id")}, inverseJoinColumns = {@JoinColumn(name = "author_id")})
    private Set<Author> authors = new HashSet<>();
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "books_categories", joinColumns = {@JoinColumn(name = "book_id")}, inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private Set<Category> categories = new HashSet<>();
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "books_publishers", joinColumns = {@JoinColumn(name = "book_id")}, inverseJoinColumns = {@JoinColumn(name = "publisher_id")})
    private Set<Publisher> publishers = new HashSet<>();

    public Book(String isbn, String name, String description) {
        this.name = name;
        this.isbn = isbn;
        this.description = description;
    }

    public void removeAuthor(final Author author) {
        authors.remove(author);
        author.getBooks().remove(author);
    }

    public void addAuthor(final Author author) {
        authors.add(author);
        author.getBooks().add(this);
    }

    public void removeCategory(final Category category) {
        categories.remove(category);
        category.getBooks().remove(category);
    }

    public void addCategory(final Category category) {
        categories.add(category);
        category.getBooks().add(this);
    }

    public void removePublisher(final Publisher publisher) {
        publishers.remove(publisher);
        publisher.getBooks().remove(publisher);
    }

    public void addPublisher(final Publisher publisher) {
        publishers.add(publisher);
        publisher.getBooks().add(this);
    }
}
