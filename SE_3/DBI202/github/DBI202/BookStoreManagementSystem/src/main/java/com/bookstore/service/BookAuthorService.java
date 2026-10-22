package com.bookstore.service;

import com.bookstore.model.BookAuthor;
import com.bookstore.model.BookAuthorId;
import com.bookstore.repository.BookAuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookAuthorService {
    private final BookAuthorRepository bookAuthorRepository;

    public List<BookAuthor> getAll() {
        return bookAuthorRepository.findAll();
    }

    public Optional<BookAuthor> getById(BookAuthorId id) {
        return bookAuthorRepository.findById(id);
    }

    @Transactional
    public BookAuthor create(BookAuthor bookAuthor) {
        return bookAuthorRepository.save(bookAuthor);
    }

    @Transactional
    public BookAuthor update(BookAuthorId id, BookAuthor bookAuthor) {
        bookAuthor.setId(id);
        return bookAuthorRepository.save(bookAuthor);
    }

    @Transactional
    public void delete(BookAuthorId id) {
        bookAuthorRepository.deleteById(id);
    }
}

