package com.bridgelabz.bookstoreapp_backend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CartModel {
    @Id
    @GeneratedValue
    private Integer cartId;
    @ManyToOne
    @JoinColumn(name = "userId")
    private UserRegistration user;
    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;
    private Integer quantity;

    public CartModel() {
    }

    public CartModel(Integer cartId, Integer quantity, Book book, UserRegistration user) {
        super();
        this.cartId = cartId;
        this.quantity = quantity;
        this.book = book;
        this.user = user;
    }

    public CartModel(Integer quantity, Book book, UserRegistration user) {
        super();
        this.quantity = quantity;
        this.book = book;
        this.user = user;
    }
}
