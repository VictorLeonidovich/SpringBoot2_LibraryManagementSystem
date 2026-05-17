package com.kvl.library.repositories;

import com.kvl.library.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends JpaRepository<Book, Long> {
    @Query(value = "SELECT DISTINCT b FROM Book b LEFT JOIN FETCH b.authors",
            countQuery = "SELECT COUNT(b) FROM Book b")
    Page<Book> findAllWithAuthors(Pageable pageable);
}
