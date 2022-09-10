package com.bridgelabz.bookstoreapp_backend.service;


import com.bridgelabz.bookstoreapp_backend.dto.BookDTO;
import com.bridgelabz.bookstoreapp_backend.repository.BookStoreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstoreapp_backend.model.Book;

import java.util.List;

@Service
public class BookService implements IBookService {

    @Autowired
    BookStoreRepository bookStoreRepository;

    @Autowired
    private ModelMapper modelMapper;



    @Override
    public Book createBook(BookDTO bookDTO) {
        Book newBook = modelMapper.map(bookDTO,Book.class );
        return bookStoreRepository.save(newBook);

    }
    @Override
    public List<Book> getAllBookData() {
        List<Book> getBooks=bookStoreRepository.findAll();
        return getBooks;
    }
    @Override
    public Book getBookDataById(int bookId) {
        return bookStoreRepository.findByBookId(bookId);
    }

    @Override
    public void deleteRecordByBookId(int bookId) {
        bookStoreRepository.deleteById(bookId);
    }

    @Override
    public Book updateRecordById(Integer bookId, BookDTO bookDTO) {
        Book bookServer =bookStoreRepository.findByBookId(bookId);
        modelMapper.map(bookDTO, bookServer);
        return bookStoreRepository.save(bookServer);
    }
    @Override
    public List<Book> getBookByName(String bookName) {
        List<Book> book = bookStoreRepository.findByBookNameContaining(bookName);
        return book;
    }
    @Override
    public List<Book> sortedListOfBooksInAscendingOrder() {
        List<Book> getSortedList=  bookStoreRepository.getSortedListOfBooksInAsc();
        return getSortedList;
    }

    @Override
    public List<Book> sortedListOfBooksInDescendingOrder() {
        List<Book> getSortedListInDesc = bookStoreRepository.getSortedListOfBooksInDesc();
        return getSortedListInDesc;
    }

}

