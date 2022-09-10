package com.bridgelabz.bookstoreapp_backend.model;

import lombok.Data;

import javax.persistence.*;

@Data

@Entity
public class Wishlist {
    @Id
    @GeneratedValue
    private Integer wishlistId;

    @ManyToOne
    @JoinColumn(name="userId")
    private UserRegistration user;
    @ManyToOne
    @JoinColumn(name="bookId")
    private Book book;

    public Wishlist() {
    }

    public Wishlist(UserRegistration user, Book book) {
        super();
        this.user = user;
        this.book = book;
    }

}
