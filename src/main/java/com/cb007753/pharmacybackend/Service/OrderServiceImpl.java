package com.cb007753.pharmacybackend.Service;

import com.cb007753.pharmacybackend.Model.Order;
import com.cb007753.pharmacybackend.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;


    //overided methods

    @Override
    public String saveOrder(Order order) {

        if(order != null)
        {
            orderRepository.save(order);
        }
        else
        {
            return "Error";
        }
        return "Error";
    }
}
