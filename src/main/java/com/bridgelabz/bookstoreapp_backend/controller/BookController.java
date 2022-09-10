package com.bridgelabz.bookstoreapp_backend.controller;

import com.bridgelabz.bookstoreapp_backend.dto.BookDTO;
import com.bridgelabz.bookstoreapp_backend.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp_backend.model.Book;
import com.bridgelabz.bookstoreapp_backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/Book")
public class BookController {

    @Autowired
    private BookService bookService;


    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> addUserInBookStore(@Valid @RequestBody BookDTO bookDTO) {
        Book newBook = bookService.createBook(bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Created the new book in book store", newBook);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }
    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllBookData() {
        List<Book> listOfBooks = bookService.getAllBookData();
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully :", listOfBooks);
        return new ResponseEntity(dto, HttpStatus.OK);
    }
    @GetMapping("/getBy/{bookId}")
    public ResponseEntity<ResponseDTO> getBookDataById(@PathVariable int bookId) {
        Book book = bookService.getBookDataById(bookId);
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully by id :", book);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{bookId}")
    public String deleteRecordById(@PathVariable int bookId) {
        bookService.deleteRecordByBookId(bookId);
        return "Book Deleted.";
    }

    @PutMapping("/updateBookById/{bookId}")
    public ResponseEntity<ResponseDTO> updateRecordById(@PathVariable Integer bookId, @Valid @RequestBody BookDTO bookDTO) {
        Book updateRecord = bookService.updateRecordById(bookId, bookDTO);
        ResponseDTO dto = new ResponseDTO(" Book Record updated successfully by Id", updateRecord);
        return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
    }
    @GetMapping(value = "searchByBookName/{bookName}")
    public ResponseEntity getBookByName(@PathVariable String bookName) {
        List<Book> fetchedRecord = bookService.getBookByName(bookName);
        ResponseDTO dto = new ResponseDTO(" Book retreived  successfully by Name", fetchedRecord);
        return new ResponseEntity(dto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/sortAsc")
    public ResponseEntity<ResponseDTO> getBooksInAscendingOrder() {
        List<Book> listOfBooks = bookService.sortedListOfBooksInAscendingOrder();
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully :", listOfBooks);
        return new ResponseEntity(dto, HttpStatus.OK);

    }

    @GetMapping("/sortDesc")
    public ResponseEntity<ResponseDTO> getBooksInDescendingOrder() {
        List<Book> listOfBooks = bookService.sortedListOfBooksInDescendingOrder();
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully :", listOfBooks);
        return new ResponseEntity(dto, HttpStatus.OK);

    }

}
