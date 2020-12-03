package com.vermeg.bookstore.controllers;

import com.vermeg.bookstore.Exceptions.BookNotFoundException;
import com.vermeg.bookstore.entities.Book;
import com.vermeg.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("books")
    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    @GetMapping("book/{id}")
    public Book getBookById(@PathVariable int id) throws BookNotFoundException {
        return bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException("The book " +
                "with the ID "+id+" does not exist"));
    }

    @PostMapping("book/add")
    public Book addBook(@RequestBody @Validated Book b,  BindingResult result) {
        if(result.hasErrors())
            System.err.println(result.getAllErrors());
        // si le produit est valide
        return bookRepository.save(b);
    }

    @DeleteMapping("book/{id}")
    public Optional<Book> deleteBook(@PathVariable int id) throws BookNotFoundException {
        Optional<Book> b = bookRepository.findById(id);
        if (b != null) {
            bookRepository.deleteById(id);
            return b;
        }
        else throw new BookNotFoundException("The book " +
                "with the ID "+id+" does not exist");
    }

    @PutMapping("book/modify/{id}")
    public void updateBook(@RequestBody @Validated Book b, @PathVariable int id,
                           BindingResult result) throws BookNotFoundException {
        if(result.hasErrors())
            System.err.println(result.getAllErrors());
        // si le produit est valide
       bookRepository.findById(id).map(book -> bookRepository.save(b))
               .orElseThrow(()-> new BookNotFoundException("The book " +
               "with the ID "+id+" does not exist"));
    }


}
