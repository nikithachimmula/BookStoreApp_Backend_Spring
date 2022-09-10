package com.bridgelabz.bookstoreapp_backend.service;

import com.bridgelabz.bookstoreapp_backend.dto.OrderDTO;
import com.bridgelabz.bookstoreapp_backend.model.Book;
import com.bridgelabz.bookstoreapp_backend.model.Order;
import com.bridgelabz.bookstoreapp_backend.model.UserRegistration;
import com.bridgelabz.bookstoreapp_backend.repository.BookStoreRepository;
import com.bridgelabz.bookstoreapp_backend.repository.OrderRepository;
import com.bridgelabz.bookstoreapp_backend.repository.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService{
    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private BookStoreRepository bookRepo;
    @Autowired
    private UserRegistrationRepository userRepo;

    public Order insertOrder(OrderDTO orderdto) {
        Optional<Book> book = bookRepo.findById(orderdto.getBookId());
        Optional<UserRegistration> user = userRepo.findById(orderdto.getUserId());
        if (book.isPresent() && user.isPresent()) {
            if (orderdto.getQuantity()< book.get().getQuantity()) {
                Order newOrder = new Order(book.get().getPrice(),orderdto.getQuantity(), orderdto.getAddress(), book.get(), user.get(), orderdto.isCancel(),book.get().getBookName());
                orderRepo.save(newOrder);
                book.get().setQuantity(book.get().getQuantity() - orderdto.getQuantity());
                return newOrder;
            } else {
                return null;//Requested quantity is not available
            }
        } else {
            return null;//Book or User doesn't exists
        }
    }
    public List<Order> getAllOrderRecords() {
        List<Order> orderList = orderRepo.findAll();
        return orderList;
    }

    public Order getOrderRecord(Integer id) {
        Optional<Order> order = orderRepo.findById(id);
        if (order.isPresent()) {
            return order.get();

        } else {
            return null;//Order Record doesn't exists
        }
    }

    public Order updateOrderRecord(Integer id, OrderDTO dto) {
        Optional<Order> order = orderRepo.findById(id);
        Optional<Book> book = bookRepo.findById(dto.getBookId());
        Optional<UserRegistration> user = userRepo.findById(dto.getUserId());
        if (order.isPresent()) {
            if (book.isPresent() && user.isPresent()) {
                if (dto.getQuantity() < book.get().getQuantity()) {
                    Order newOrder = new Order(id, dto.getQuantity(), dto.getAddress(), book.get(), user.get(), dto.isCancel(),book.get().getBookName());
                    orderRepo.save(newOrder);
                    return newOrder;
                } else {
                    return null;//Requested quantity is not available
                }
            } else {
                return null;//Book or User doesn't exists

            }

        } else {
            return null;//Order Record doesn't exist
        }
    }

    public Order deleteOrderRecord(Integer id) {
        Optional<Order> order = orderRepo.findById(id);
        if (order.isPresent()) {
            orderRepo.deleteById(id);
            return order.get();

        } else {
            return null;//Order Record doesn't exists
        }
    }
}
