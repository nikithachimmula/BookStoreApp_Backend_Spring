package com.bridgelabz.bookstoreapp_backend.repository;


import com.bridgelabz.bookstoreapp_backend.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BookStoreRepository extends JpaRepository<Book,Integer> {

    public  Book findByBookId(int bookId);

    List<Book> findByBookNameContaining(String bookName);

    @Query(value = "select * from book order by price", nativeQuery = true)
    List<Book> getSortedListOfBooksInAsc();

    @Query(value = "select * from book order by price desc", nativeQuery = true)
    List<Book> getSortedListOfBooksInDesc();

}