package com.bridgelabz.bookstoreapp_backend.repository;

import com.bridgelabz.bookstoreapp_backend.model.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookStoreCartRepository extends JpaRepository<CartModel,Integer>{
}
