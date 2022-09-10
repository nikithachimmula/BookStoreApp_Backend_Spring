package com.bridgelabz.bookstoreapp_backend.service;

import com.bridgelabz.bookstoreapp_backend.dto.OrderDTO;
import com.bridgelabz.bookstoreapp_backend.model.Order;

import java.util.List;

public interface IOrderService {
    public Order insertOrder(OrderDTO orderdto);

    public List<Order> getAllOrderRecords();

    public Order getOrderRecord(Integer id);

    public Order updateOrderRecord(Integer id, OrderDTO dto);

    public Order deleteOrderRecord(Integer id);
}
