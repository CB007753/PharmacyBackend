package com.cb007753.pharmacybackend.Service;

import com.cb007753.pharmacybackend.Model.Order;

import java.util.List;

public interface OrderService {

    //this function will used to save and update order
    String saveOrder(Order order);

    void deleteOrder(Order order);

    void deleteDrug(Long id);

    List<Order> getOrderByEmailAndStatus(String email, String status);

}
