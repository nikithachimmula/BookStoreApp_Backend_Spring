package com.bridgelabz.bookstoreapp_backend.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BookDTO {
    @NotNull(message = "book name cant be null")
    private String bookName;

    @NotNull(message = "author name cant be null")
    private String author;

    @NotNull(message = "book description cant be null")
    private String bookDescription;

    @NotNull(message = "book image cant be null")
    private String bookLogo;

    @NotNull(message = "price cant be empty")
    private Integer price;

    @NotNull(message = "Quantity cant be empty")
    private Integer quantity;
}