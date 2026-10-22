package com.bookstore.service;

import com.bookstore.model.Author;
import com.bookstore.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.CallableStatement;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final JdbcTemplate jdbcTemplate;

    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    public Optional<Author> getById(Integer authorId) {
        return authorRepository.findById(authorId);
    }

    @Transactional
    public Author create(Author author) {
        return authorRepository.save(author);
    }

    @Transactional
    public Author update(Integer authorId, Author author) {
        author.setAuthorId(authorId);
        return authorRepository.save(author);
    }

    @Transactional
    public void delete(Integer authorId) {
        authorRepository.deleteById(authorId);
    }

    @Transactional
    public void deleteAuthorUsingStoredProcedure(Integer authorId) {
        jdbcTemplate.update(conn -> {
            CallableStatement cs = conn.prepareCall("{call sp_DeleteAuthor(?)}");
            cs.setInt(1, authorId);
            return cs;
        });
    }
}

