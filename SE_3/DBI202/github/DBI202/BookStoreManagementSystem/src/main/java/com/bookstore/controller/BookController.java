package com.bookstore.controller;

import com.bookstore.model.Book;
import com.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    @GetMapping
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/{bookId}")
    public Book getById(@PathVariable Integer bookId) {
        return bookService.getById(bookId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        return bookService.create(book);
    }

    @PutMapping("/{bookId}")
    public Book update(@PathVariable Integer bookId, @RequestBody Book book) {
        return bookService.update(bookId, book);
    }

    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer bookId) {
        bookService.delete(bookId);
    }

    /**
     * Uses stored procedure sp_BulkUpdateBookPrice.
     */
    @PostMapping("/{bookId}/bulk-price-update")
    public Book bulkUpdatePrice(
            @PathVariable Integer bookId,
            @RequestParam("percentageChange") BigDecimal percentageChange
    ) {
        return bookService.bulkUpdateBookPrice(bookId, percentageChange);
    }
}

