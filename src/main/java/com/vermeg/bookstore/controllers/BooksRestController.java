package com.vermeg.bookstore.controllers;

import com.vermeg.bookstore.entities.Book;
import com.vermeg.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookstore/")
public class BooksRestController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("books")
    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    @GetMapping("book/{id}")
    public Book getBookById(@PathVariable int id){
        return bookRepository.findById(id).orElseThrow(()-> new IllegalArgumentException());
    }

    @PostMapping("book/add")
    public void addBook(@RequestBody @Validated Book b,  BindingResult result) {
        if(result.hasErrors())
            System.err.println(result.getAllErrors());
        // si le produit est valide
        bookRepository.save(b);
        System.out.println("Book added successfully");
    }

    @DeleteMapping("book/{id}")
    public void deleteBook(@PathVariable int id){
        bookRepository.deleteById(id);
    }

    @PutMapping("book/modify/{id}")
    public void updateBook(@RequestBody @Validated Book b, @PathVariable int Id,
                           BindingResult result) {
        if(result.hasErrors())
            System.err.println(result.getAllErrors());
        // si le produit est valide
        bookRepository.save(b);
        System.out.println("Book update successfully");
    }
}
