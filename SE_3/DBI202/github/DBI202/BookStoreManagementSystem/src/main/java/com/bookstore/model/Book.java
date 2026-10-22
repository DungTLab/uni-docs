package com.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "BOOK")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book implements Serializable {
    @Id
    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "public_year")
    private Integer publicYear;

    @Column(name = "quantity")
    private Integer quantity;
}

