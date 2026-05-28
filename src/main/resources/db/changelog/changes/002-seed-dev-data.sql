--liquibase formatted sql

--changeset victor:2-insert-data context:dev
INSERT INTO authors (id, name, description) VALUES
                                                (1, 'Author1', 'Author1 description'),
                                                (2, 'Author2', 'Author2 description'),
                                                (3, 'Author3', 'Author3 description'),
                                                (4, 'Author4', 'Author4 description'),
                                                (5, 'Author5', 'Author5 description'),
                                                (6, 'Author6', 'Author6 description');

INSERT INTO categories (id, name) VALUES
                                      (1, 'Category1'),
                                      (2, 'Category2'),
                                      (3, 'Category3'),
                                      (4, 'Category4'),
                                      (5, 'Category5'),
                                      (6, 'Category6');

INSERT INTO publishers (id, name) VALUES
                                      (1, 'Publisher1'),
                                      (2, 'Publisher2'),
                                      (3, 'Publisher3'),
                                      (4, 'Publisher4'),
                                      (5, 'Publisher5'),
                                      (6, 'Publisher6');

INSERT INTO books (id, name, isbn, description) VALUES
                                                    (1, 'Book1 name', 'isbn1', 'Book1 description'),
                                                    (2, 'Book2 name', 'isbn3', 'Book2 description'),
                                                    (3, 'Book3 name', 'isbn2', 'Book3 description'),
                                                    (4, 'Book4 name', 'isbn6', 'Book4 description'),
                                                    (5, 'Book5 name', 'isbn5', 'Book5 description'),
                                                    (6, 'Book6 name', 'isbn4', 'Book6 description');

INSERT INTO books_authors (book_id, author_id) VALUES
                                                   (1, 1), (2, 2), (3, 3), (4, 4), (5, 5), (6, 6);

INSERT INTO books_categories (book_id, category_id) VALUES
                                                        (1, 1), (2, 2), (3, 3), (4, 4), (5, 5), (6, 6);

INSERT INTO books_publishers (book_id, publisher_id) VALUES
                                                         (1, 1), (2, 2), (3, 3), (4, 4), (5, 5), (6, 6);

ALTER TABLE authors ALTER COLUMN id RESTART WITH 7;
ALTER TABLE categories ALTER COLUMN id RESTART WITH 7;
ALTER TABLE publishers ALTER COLUMN id RESTART WITH 7;
ALTER TABLE books ALTER COLUMN id RESTART WITH 7;

-- Добавление тестового пользователя (логин: user, пароль: password)
INSERT INTO users (username, password, role)
VALUES ('user', '$2a$10$8.UnVuG9HHgN3vN6Y8.vXOCyBvB9qA78Yk7PBM9B.VreApxvM4B66', 'ROLE_USER');

-- Добавление администратора (логин: admin, пароль: password)
INSERT INTO users (username, password, role)
VALUES ('admin', '$2a$10$8.UnVuG9HHgN3vN6Y8.vXOCyBvB9qA78Yk7PBM9B.VreApxvM4B66', 'ROLE_ADMIN');