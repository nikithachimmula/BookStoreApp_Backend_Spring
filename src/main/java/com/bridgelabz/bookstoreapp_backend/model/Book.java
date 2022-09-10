package com.bridgelabz.bookstoreapp_backend.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bookId;

    private String bookName;

    private String author;

    private String bookDescription;

    private String bookLogo;

    private Integer price;

    private Integer quantity;

}