package com.bookstore.controller;

import com.bookstore.model.Author;
import com.bookstore.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    public List<Author> getAll() {
        return authorService.getAll();
    }

    @GetMapping("/{authorId}")
    public Author getById(@PathVariable Integer authorId) {
        return authorService.getById(authorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author create(@RequestBody Author author) {
        return authorService.create(author);
    }

    @PutMapping("/{authorId}")
    public Author update(@PathVariable Integer authorId, @RequestBody Author author) {
        return authorService.update(authorId, author);
    }

    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer authorId) {
        authorService.deleteAuthorUsingStoredProcedure(authorId);
    }
}

