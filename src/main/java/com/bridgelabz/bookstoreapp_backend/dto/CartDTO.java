package com.bridgelabz.bookstoreapp_backend.dto;

import lombok.Data;


@Data
public class CartDTO {

    private Integer userId;
    private Integer bookId;

    private Integer quantity;
}
