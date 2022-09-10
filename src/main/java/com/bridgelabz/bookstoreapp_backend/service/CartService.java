package com.bridgelabz.bookstoreapp_backend.service;

import com.bridgelabz.bookstoreapp_backend.dto.CartDTO;
import com.bridgelabz.bookstoreapp_backend.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp_backend.model.Book;
import com.bridgelabz.bookstoreapp_backend.model.CartModel;
import com.bridgelabz.bookstoreapp_backend.model.UserRegistration;
import com.bridgelabz.bookstoreapp_backend.repository.BookStoreCartRepository;
import com.bridgelabz.bookstoreapp_backend.repository.BookStoreRepository;
import com.bridgelabz.bookstoreapp_backend.repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CartService implements ICartService {
    @Autowired
    BookStoreRepository bookStoreRepository;
    @Autowired
    UserRegistrationRepository userRegistrationRepository;
    @Autowired
    BookStoreCartRepository bookStoreCartRepository;

    @Override
    public CartModel insertItems(CartDTO cartdto) {
        Optional<Book> book = bookStoreRepository.findById(cartdto.getBookId());
        Optional<UserRegistration> userRegistration = userRegistrationRepository.findById(cartdto.getUserId());
        if(book.isPresent() && userRegistration.isPresent()) {
            CartModel newCart = new CartModel(cartdto.getQuantity(), book.get(), userRegistration.get());
            if (cartdto.getQuantity() <= newCart.getQuantity() && cartdto.getQuantity()>0  ) {
                book.get().setQuantity((book.get().getQuantity()) - (cartdto.getQuantity()));
                bookStoreRepository.save(book.get());
                bookStoreCartRepository.save(newCart);
                return newCart;
            }
            return null;
        } else {
            return null;
        }
    }


    @Override
    public ResponseDTO getCartDetails() {
        List<CartModel> getCartDetails = bookStoreCartRepository.findAll();
        ResponseDTO dto = new ResponseDTO();
        if (getCartDetails.isEmpty()) {
            String message = " the cart is empty!!!";
            dto.setMessage(message);
            dto.setData(0);
            return dto;

        } else {
            dto.setMessage("the list of cart items is sucussfully retrived");
            dto.setData(getCartDetails);
            return dto;
        }
    }


    @Override
    public Optional<CartModel> getCartDetailsById(Integer cartId) {
        Optional<CartModel> getCartData = bookStoreCartRepository.findById(cartId);
        if (getCartData.isPresent()) {
            return getCartData;
        } else {
            return Optional.empty(); //Didn't find any record for this particular cartId
        }
    }

    @Override
    public Optional<CartModel> deleteCartItemById(Integer cartId) {
        Optional<CartModel> deleteData = bookStoreCartRepository.findById(cartId);
        if (deleteData.isPresent()) {
            bookStoreCartRepository.deleteById(cartId);
            return deleteData;
        } else {
            return Optional.empty();// Did not get any cart for specific cart id
        }

    }

    @Override
    public CartModel updateRecordById(Integer cartId, CartDTO cartDTO) {
        Optional<CartModel> cart = bookStoreCartRepository.findById(cartId);
        Optional<Book> book = bookStoreRepository.findById(cartDTO.getBookId());
        Optional<UserRegistration> user = userRegistrationRepository.findById(cartDTO.getUserId());
        if (cart.isPresent()) {
            if (book.isPresent() && user.isPresent()) {
                CartModel newCart = new CartModel(cartId, cartDTO.getQuantity(), book.get(), user.get());
                if (cartDTO.getQuantity() <= newCart.getQuantity()) {
                    book.get().setQuantity((book.get().getQuantity()) - (cartDTO.getQuantity()));
                    bookStoreRepository.save(book.get());
                    bookStoreCartRepository.save(newCart);
                    return newCart;
                }
                return null;// when quantity more
            }
            return null;//book or user is not present
        }
        return null;// cart is not there
    }


    @Override
    public CartModel updateQuantity(Integer id, Integer quantity) {
        Optional<CartModel> cart = bookStoreCartRepository.findById(id);
        Optional<Book> book = bookStoreRepository.findById(cart.get().getBook().getBookId());
        if (cart.isPresent()) {
//            if(quantity < book.get().getQuantity()) {
            if ((book.get().getQuantity()) > 0) {
                book.get().setQuantity(book.get().getQuantity() - (quantity - cart.get().getQuantity()));
                cart.get().setQuantity(quantity);
                bookStoreCartRepository.save(cart.get());
                bookStoreRepository.save(book.get());
                return cart.get();
            } else {
                return null;//No sufficient quantity
            }
        } else {
            return null;//cart dosent exist
        }
    }
    @Override
    public Optional<CartModel> deleteCartItemByIdAndQuantity(Integer cartId,Integer quantity) {
        Optional<CartModel> deleteData=bookStoreCartRepository.findById(cartId);
        if (deleteData.isPresent()){
            Optional<Book>  book = bookStoreRepository.findById(deleteData.get().getBook().getBookId());
            System.out.println("book.get().getQuantity() + quantity"+(book.get().getQuantity() + quantity));
            book.get().setQuantity(book.get().getQuantity() + quantity);
            bookStoreRepository.save(book.get());
            bookStoreCartRepository.deleteById(cartId);
            return deleteData;
        }
        else {
            return Optional.empty();// Did not get any cart for specific cart id
        }

    }
}
