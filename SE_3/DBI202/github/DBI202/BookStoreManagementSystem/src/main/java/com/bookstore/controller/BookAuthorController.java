package com.bookstore.controller;

import com.bookstore.model.BookAuthor;
import com.bookstore.model.BookAuthorId;
import com.bookstore.service.BookAuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book-authors")
public class BookAuthorController {
    private final BookAuthorService bookAuthorService;

    @GetMapping
    public List<BookAuthor> getAll() {
        return bookAuthorService.getAll();
    }

    @GetMapping("/{bookId}/{authorId}")
    public BookAuthor getById(@PathVariable Integer bookId, @PathVariable Integer authorId) {
        BookAuthorId id = new BookAuthorId(bookId, authorId);
        return bookAuthorService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "BookAuthor not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookAuthor create(@RequestBody BookAuthor bookAuthor) {
        return bookAuthorService.create(bookAuthor);
    }

    @PutMapping("/{bookId}/{authorId}")
    public BookAuthor update(
            @PathVariable Integer bookId,
            @PathVariable Integer authorId,
            @RequestBody BookAuthor bookAuthor
    ) {
        BookAuthorId id = new BookAuthorId(bookId, authorId);
        return bookAuthorService.update(id, bookAuthor);
    }

    @DeleteMapping("/{bookId}/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer bookId, @PathVariable Integer authorId) {
        bookAuthorService.delete(new BookAuthorId(bookId, authorId));
    }
}

