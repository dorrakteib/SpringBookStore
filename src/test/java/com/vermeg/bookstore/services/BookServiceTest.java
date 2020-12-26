package com.vermeg.bookstore.services;

import com.vermeg.bookstore.entities.Book;
import com.vermeg.bookstore.repositories.BookRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class BookServiceTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookService bookService;

    Book book;
    List<Book> books = new ArrayList<>();

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Start testing the book service");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("End testing the book service");
    }

    @BeforeEach
    public void setUp() {
        System.out.println("The test started");
        book = new Book(new Long(1), "L'alshimiste", "Paulo Coelho", 30);
        books.add(book);
    }

    @AfterEach
    public void tearDown() {
        System.out.println("The test ended");
    }

    @Test
    public void getAllTest() {

        when(this.bookRepository.findAll()).thenReturn(books);
        assertTrue(this.bookService.getAll().size() == books
                .size(), "Test failed: Size of list isn't equal to the size of the " +
                "present test");
    }

    @Test
    public void getBookByIdTest() {
        when(this.bookRepository.findById(book.getId())).thenReturn(java.util.Optional
                .ofNullable(book));
        assertEquals(1, book.getId());
        assertSame(book, this.bookService.getBookById(book.getId()),
                "Test failed: Not matching Book ID");
        System.out.println(this.bookService.getBookById(book.getId()).toString());
    }

    @Test
    public void addBookTest() {
        Book book = new Book("book","book's author",4, new Date());
        books.add(book);
        bookService.addBook(book);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void deleteBookTest() {
        bookService.deleteBook(2L);
        verify(bookRepository, times(2)).deleteById(2L);
    }

    @Test
    public void updateBookTest() {

        Book book = new Book(1L, "L'alshimiste", "Paulo Coelho", 20);
        bookService.updateBook(book, 1L);
        verify(bookRepository, times(1)).save(book);
    }
}