package com.bridgelabz.bookstoreapp_backend.service;

import com.bridgelabz.bookstoreapp_backend.dto.CartDTO;
import com.bridgelabz.bookstoreapp_backend.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp_backend.model.CartModel;

import java.util.Optional;

public interface ICartService {
    ResponseDTO getCartDetails();


    Optional<CartModel> getCartDetailsById(Integer cartId);

    CartModel insertItems(CartDTO cartdto);

    Optional<CartModel> deleteCartItemById(Integer cartId);

    CartModel updateRecordById(Integer cartId, CartDTO cartDTO);

    CartModel updateQuantity(Integer id, Integer quantity);

    Optional<CartModel> deleteCartItemByIdAndQuantity(Integer cartId, Integer quantity);
}
