package com.vermeg.bookstore.controllers;

import com.vermeg.bookstore.entities.Book;
import com.vermeg.bookstore.repositories.BookRepository;
import com.vermeg.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookstore/")
public class BooksRestController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @GetMapping("books")
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @GetMapping("book/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping("book/add")
    public Book addBook(@RequestBody @Validated Book b, BindingResult result) {
        if (result.hasErrors())
            System.err.println(result.getAllErrors());
        return bookService.addBook(b);
    }

    @DeleteMapping("book/{id}")
    public Book deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id);
    }

    @PutMapping("book/modify/{id}")
    public void updateBook(@RequestBody @Validated Book b, @PathVariable Long id,
                           BindingResult result) {
        if (result.hasErrors())
            System.err.println(result.getAllErrors());
        bookService.updateBook(b, id);
    }
}
