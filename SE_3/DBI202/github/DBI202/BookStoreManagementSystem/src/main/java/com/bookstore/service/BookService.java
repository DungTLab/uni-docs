package com.bookstore.service;

import com.bookstore.model.Book;
import com.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final JdbcTemplate jdbcTemplate;

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> getById(Integer bookId) {
        return bookRepository.findById(bookId);
    }

    @Transactional
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public Book update(Integer bookId, Book book) {
        book.setBookId(bookId);
        return bookRepository.save(book);
    }

    @Transactional
    public void delete(Integer bookId) {
        bookRepository.deleteById(bookId);
    }

    @Transactional
    public Book bulkUpdateBookPrice(int bookId, BigDecimal percentageChange) {
        jdbcTemplate.update(conn -> {
            CallableStatement cs = conn.prepareCall("{call sp_BulkUpdateBookPrice(?, ?)}");
            cs.setBigDecimal(1, percentageChange);
            cs.setInt(2, bookId);
            return cs;
        });

        return bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalStateException("Book not found after bulk price update"));
    }
}

