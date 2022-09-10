package com.bridgelabz.bookstoreapp_backend.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Order {
    @Id
    @GeneratedValue
    private Integer orderID;
    private LocalDate date = LocalDate.now();
    private Integer price;
    private Integer quantity;
    private String address;
    @ManyToOne
    @JoinColumn(name="userId")
    private UserRegistration user;
    @ManyToOne
    @JoinColumn(name="bookId")
    private Book book;
    private Boolean cancel;
    private String bookName;

    public Order(Integer price,Integer quantity, String address, Book book, UserRegistration user, Boolean cancel,String bookName) {
        this.price=price;
        this.quantity=quantity;
        this.address=address;
        this.book = book;
        this.user=user;
        this.cancel = cancel;
        this.bookName=bookName;
    }



    public Order() {
        super();
    }

    public Order( Integer price,Integer quantity, String address, Book book, UserRegistration user, Boolean cancel) {
        this.price=price;
        this.quantity=quantity;
        this.address=address;
        this.book = book;
        this.user=user;
        this.cancel = cancel;
    }
}
