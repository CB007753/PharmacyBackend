package com.cb007753.pharmacybackend.Service;

import com.cb007753.pharmacybackend.Model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    //this function will used to save and update order
    String saveOrder(Order order);

    void deleteOrder(Order order);

    Optional<Order> getOrderByID(Long id);

    void deleteDrug(Long id);

    List<Order> getOrderByEmailAndStatus(String email, String status);

}
