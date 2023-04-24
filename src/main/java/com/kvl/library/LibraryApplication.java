package com.kvl.library;

import com.kvl.library.entity.Author;
import com.kvl.library.entity.Book;
import com.kvl.library.entity.Category;
import com.kvl.library.entity.Publisher;
import com.kvl.library.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    @Bean
    public CommandLineRunner initialCreate(final BookService bookService) {
        return (args) -> {
            Book book1 = new Book("Book1", "Book1 name", "Book1 description");
            Author author1 = new Author("Author1", "Author1 description");
            Category category1 = new Category("Category1");
            Publisher publisher1 = new Publisher("Publisher1");
            book1.addAuthor(author1);
            book1.addCategory(category1);
            book1.addPublisher(publisher1);
            bookService.createBook(book1);

            Book book2 = new Book("Book2", "Book2 name", "Book2 description");
            Author author2 = new Author("Author2", "Author2 description");
            Category category2 = new Category("Category2");
            Publisher publisher2 = new Publisher("Publisher2");
            book2.addAuthor(author2);
            book2.addCategory(category2);
            book2.addPublisher(publisher2);
            bookService.createBook(book2);

            Book book3 = new Book("Book3", "Book3 name", "Book3 description");
            Author author3 = new Author("Author3", "Author3 description");
            Category category3 = new Category("Category3");
            Publisher publisher3 = new Publisher("Publisher3");
            book3.addAuthor(author3);
            book3.addCategory(category3);
            book3.addPublisher(publisher3);
            bookService.createBook(book3);
        };
    }
}
