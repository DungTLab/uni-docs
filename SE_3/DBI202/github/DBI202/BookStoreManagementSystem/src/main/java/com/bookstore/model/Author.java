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

@Entity
@Table(name = "AUTHOR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author implements Serializable {
    @Id
    @Column(name = "author_id")
    private Integer authorId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "nationality", length = 50)
    private String nationality;
}

