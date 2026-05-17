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
            Book book1 = new Book("isbn1", "Book1 name", "Book1 description");
            Author author1 = new Author("Author1", "Author1 description");
            Category category1 = new Category("Category1");
            Publisher publisher1 = new Publisher("Publisher1");
            book1.addAuthor(author1);
            book1.addCategory(category1);
            book1.addPublisher(publisher1);
            bookService.createBook(book1);

            Book book2 = new Book("isbn3", "Book2 name", "Book2 description");
            Author author2 = new Author("Author2", "Author2 description");
            Category category2 = new Category("Category2");
            Publisher publisher2 = new Publisher("Publisher2");
            book2.addAuthor(author2);
            book2.addCategory(category2);
            book2.addPublisher(publisher2);
            bookService.createBook(book2);

            Book book3 = new Book("isbn2", "Book3 name", "Book3 description");
            Author author3 = new Author("Author3", "Author3 description");
            Category category3 = new Category("Category3");
            Publisher publisher3 = new Publisher("Publisher3");
            book3.addAuthor(author3);
            book3.addCategory(category3);
            book3.addPublisher(publisher3);
            bookService.createBook(book3);

            Book book4 = new Book("isbn6", "Book4 name", "Book4 description");
            Author author4 = new Author("Author4", "Author4 description");
            Category category4 = new Category("Category4");
            Publisher publisher4 = new Publisher("Publisher4");
            book4.addAuthor(author4);
            book4.addCategory(category4);
            book4.addPublisher(publisher4);
            bookService.createBook(book4);

            Book book5 = new Book("isbn5", "Book5 name", "Book5 description");
            Author author5 = new Author("Author5", "Author5 description");
            Category category5 = new Category("Category5");
            Publisher publisher5 = new Publisher("Publisher5");
            book5.addAuthor(author5);
            book5.addCategory(category5);
            book5.addPublisher(publisher5);
            bookService.createBook(book5);

            Book book6 = new Book("isbn4", "Book6 name", "Book6 description");
            Author author6 = new Author("Author6", "Author6 description");
            Category category6 = new Category("Category6");
            Publisher publisher6 = new Publisher("Publisher6");
            book6.addAuthor(author6);
            book6.addCategory(category6);
            book6.addPublisher(publisher6);
            bookService.createBook(book6);
        };
    }
}
