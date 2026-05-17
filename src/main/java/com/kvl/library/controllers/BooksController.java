package com.kvl.library.controllers;

import com.kvl.library.entity.Book;
import com.kvl.library.service.AuthorService;
import com.kvl.library.service.CategoryService;
import com.kvl.library.service.PublisherService;
import com.kvl.library.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;
    private CategoryService categoryService;
    private PublisherService publisherService;
    private AuthorService authorService;

    @Autowired
    public BooksController(BooksService booksService, CategoryService categoryService, PublisherService publisherService, AuthorService authorService) {
        this.booksService = booksService;
        this.categoryService = categoryService;
        this.publisherService = publisherService;
        this.authorService = authorService;
    }

    @GetMapping()
    public String index(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "books_per_page", defaultValue = "5") int booksPerPage,
            @RequestParam(value = "sort_by", defaultValue = "name") String sortBy,
            @RequestParam(value = "order", defaultValue = "asc") String order,
            Model model) {

        Page<Book> bookPage = booksService.findWithPaginationAndSorting(page, booksPerPage, sortBy, order);

        // Передаем данные и метаданные пагинации в Thymeleaf шаблон
        model.addAttribute("books", bookPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bookPage.getTotalPages());
        model.addAttribute("booksPerPage", booksPerPage);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("order", order);

        return "books/index";
    }

    @GetMapping("/book/{id}")
    public String findBook(@PathVariable Long id, Model model) {
        Book book = booksService.findBookById(id);
        model.addAttribute("book", book);
        return "books/list-book";
    }

    // 2. Удаление книги (Безопаснее делать через @DeleteMapping, но реализуем через ваш @GetMapping)
    @GetMapping("/remove-book/{id}")
    public String deleteBook(@PathVariable Long id) {
        booksService.deleteBook(id);
        // Делаем перенаправление, чтобы сработал основной метод index() с пагинацией
        return "redirect:/books";
    }

    // 3. Страница редактирования книги (передаем саму книгу и списки связей для выпадающих списков/чекбоксов)
    @GetMapping("/update-book/{id}")
    public String updateBook(@PathVariable Long id, Model model) {
        model.addAttribute("book", booksService.findBookById(id));
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("publishers", publisherService.findAllPublishers());
        model.addAttribute("authors", authorService.findAllAuthors());
        return "books/update-book";
    }

    // 4. Сохранение изменений при редактировании книги
    @PostMapping("/save-book/{id}")
    public String updateBook(@PathVariable Long id, @Valid Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // Если есть ошибки валидации, возвращаем списки связей заново, чтобы форма не сломалась
            model.addAttribute("categories", categoryService.findAllCategories());
            model.addAttribute("publishers", publisherService.findAllPublishers());
            model.addAttribute("authors", authorService.findAllAuthors());
            return "books/update-book";
        }
        booksService.updateBook(book);
        return "redirect:/books";
    }

    // 5. Страница добавления новой книги
    @GetMapping("/add-book")
    public String addBook(Model model) {
        model.addAttribute("book", new Book()); // Передаем пустой объект для связывания с формой th:object
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("publishers", publisherService.findAllPublishers());
        model.addAttribute("authors", authorService.findAllAuthors());
        return "books/add-book";
    }

    // 6. Сохранение новой созданной книги
    @PostMapping("/save-book")
    public String saveBook(@Valid Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.findAllCategories());
            model.addAttribute("publishers", publisherService.findAllPublishers());
            model.addAttribute("authors", authorService.findAllAuthors());
            return "books/add-book";
        }
        booksService.createBook(book);
        return "redirect:/books";
    }
}