package com.bridgelabz.bookstoreapp_backend.controller;

import com.bridgelabz.bookstoreapp_backend.dto.CartDTO;
import com.bridgelabz.bookstoreapp_backend.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp_backend.model.CartModel;
import com.bridgelabz.bookstoreapp_backend.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/cart")
@RestController
public class CartController {
    @Autowired
    ICartService cartService;

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> insertItem(@Valid @RequestBody CartDTO cartdto) {
        System.out.println(cartdto);
        CartModel newCart = cartService.insertItems(cartdto);
        ResponseDTO responseDTO = new ResponseDTO("User registered successfully !", newCart);
        System.out.println("AFTER"+responseDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseDTO getAllCartDetails() {
        ResponseDTO responseDTO = cartService.getCartDetails();
        return responseDTO;
    }


    @GetMapping("/getById/{cartId}")
    public ResponseEntity<ResponseDTO> getCartDetailsById(@PathVariable Integer cartId) {
        Optional<CartModel> specificCartDetail = cartService.getCartDetailsById(cartId);
        ResponseDTO responseDTO = new ResponseDTO("Cart details retrieved successfully", specificCartDetail);
        return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<ResponseDTO> deleteCartById(@PathVariable Integer cartId) {
        Optional<CartModel> delete = cartService.deleteCartItemById(cartId);
        ResponseDTO responseDTO = new ResponseDTO("Cart delete successfully", delete);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/updateById/{cartId}")
    public ResponseEntity<ResponseDTO> updateCartById(@PathVariable Integer cartId,
                                                      @Valid @RequestBody CartDTO cartDTO) {
        CartModel updateRecord = cartService.updateRecordById(cartId, cartDTO);
        ResponseDTO dto = new ResponseDTO(" Cart Record updated successfully by Id", updateRecord);
        return new ResponseEntity(dto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/updateCartQuantity/{id}")
    public ResponseEntity<ResponseDTO> updateQuantity(@PathVariable Integer id, @RequestParam Integer quantity) {
        CartModel newCart = cartService.updateQuantity(id, quantity);
        ResponseDTO dto = new ResponseDTO("Quantity for book record updated successfully !", newCart);
        return new ResponseEntity(dto, HttpStatus.OK);
    }
    @DeleteMapping("/deleteByIdAndQuantity/{cartId}")
    public ResponseEntity<ResponseDTO> deleteCartByIdAndQuantity(@PathVariable Integer cartId, @RequestParam Integer quantity) {
        Optional<CartModel> delete = cartService.deleteCartItemByIdAndQuantity(cartId,quantity);
        ResponseDTO responseDTO = new ResponseDTO("Cart delete successfully", delete);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
