package com.vermeg.bookstore.DAO;

import com.vermeg.bookstore.entities.Book;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.List;

public interface BookDAO {

    public List<Book> getAll();

    public Book getBookById(Long id) throws ResourceNotFoundException;

    public Book addBook(Book b);

    public Book deleteBook(Long id) throws ResourceNotFoundException;

    public Book updateBook(Book b,  Long id) throws ResourceNotFoundException;


}
