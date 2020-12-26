package com.vermeg.bookstore.controllers;

import com.vermeg.bookstore.entities.Book;
import com.vermeg.bookstore.repositories.BookRepository;
import com.vermeg.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookstore/")
public class BooksRestController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @GetMapping("")
    public ResponseEntity<List<Book>> getAll() {
        return new ResponseEntity<>(bookService.getAll(), HttpStatus.OK);
    }

    @GetMapping("book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    }

    @PostMapping("book/add")
    public ResponseEntity<Book> addBook(@RequestBody @Validated Book b, BindingResult result) {
        if (result.hasErrors())
            System.err.println(result.getAllErrors());
        return new ResponseEntity<>(bookService.addBook(b), HttpStatus.CREATED);
    }

    @DeleteMapping("book/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.deleteBook(id), HttpStatus.OK);
    }

    @PutMapping("book/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody @Validated Book b, @PathVariable Long id,
                           BindingResult result) {
        if (result.hasErrors())
            System.err.println(result.getAllErrors());
        return new ResponseEntity<>(bookService.updateBook(b, id), HttpStatus.OK);
    }
}
