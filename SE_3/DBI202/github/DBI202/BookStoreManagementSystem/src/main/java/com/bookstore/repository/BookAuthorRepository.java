package com.bookstore.repository;

import com.bookstore.model.BookAuthor;
import com.bookstore.model.BookAuthorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookAuthorRepository extends JpaRepository<BookAuthor, BookAuthorId> {
}

