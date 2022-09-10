package com.bridgelabz.bookstoreapp_backend.service;

import com.bridgelabz.bookstoreapp_backend.dto.WishListDTO;
import com.bridgelabz.bookstoreapp_backend.model.Book;
import com.bridgelabz.bookstoreapp_backend.model.UserRegistration;
import com.bridgelabz.bookstoreapp_backend.model.Wishlist;
import com.bridgelabz.bookstoreapp_backend.repository.BookStoreRepository;
import com.bridgelabz.bookstoreapp_backend.repository.UserRegistrationRepository;
import com.bridgelabz.bookstoreapp_backend.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class WishlistService {
    @Autowired
    private WishListRepository WishlistRepo;

    @Autowired
    private UserRegistrationRepository userRepo;

    @Autowired
    private BookStoreRepository bookRepo;

    public Wishlist addToWishlist(WishListDTO dto) {
        // TODO Auto-generated method stub
        Optional<UserRegistration> user = userRepo.findById(dto.getUserId());
        Optional<Book> book = bookRepo.findById(dto.getBookId());
        if (user.isPresent() && book.isPresent()) {
            Wishlist newWishList = new Wishlist(user.get(), book.get());
            WishlistRepo.save(newWishList);
            return newWishList;
        } else {
            return null;
        }
    }


    public List<Wishlist> getAllWishlists() {
        List<Wishlist> list = WishlistRepo.findAll();
        return list;
    }

    public List<Wishlist> getWishlistRecordById(Integer id) {
        List<Wishlist> list = WishlistRepo.findByWishlistId(id);
        if (list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }

    public List<Wishlist> getWishlistRecordByBookId(Integer bookId) {
        List<Wishlist> list = WishlistRepo.findByBookId(bookId);
        if (list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }

    public List<Wishlist> getWishlistRecordByUserId(Integer userId) {
        List<Wishlist> list = WishlistRepo.findByUserId(userId);
        return list;

    }

    public Wishlist deleteWishlistRecordById(Integer Id) {
        Optional<Wishlist> list = WishlistRepo.findById(Id);
        WishlistRepo.deleteById(Id);
        return list.get();
    }
}
