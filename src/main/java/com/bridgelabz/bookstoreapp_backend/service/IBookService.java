package com.bridgelabz.bookstoreapp_backend.service;

import com.bridgelabz.bookstoreapp_backend.dto.BookDTO;
import com.bridgelabz.bookstoreapp_backend.model.Book;

import java.util.List;

public interface IBookService {

    Book createBook(BookDTO bookDTO);

    List<Book> getAllBookData();
    Book getBookDataById(int bookId);

    void deleteRecordByBookId(int bookId);


    Book updateRecordById(Integer bookId, BookDTO bookDTO);

    List<Book> getBookByName(String bookName);

    List<Book> sortedListOfBooksInAscendingOrder();

    List<Book> sortedListOfBooksInDescendingOrder();
}
