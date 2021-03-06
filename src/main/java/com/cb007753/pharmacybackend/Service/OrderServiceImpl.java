package com.cb007753.pharmacybackend.Service;

import com.cb007753.pharmacybackend.Model.Order;
import com.cb007753.pharmacybackend.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    @Override
    public void deleteOrder(Order order) {

            orderRepository.delete(order);

    }

    @Override
    public Optional<Order> getOrderByID(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public void deleteDrug(Long id) {
        //deletes the order which matches the provided id
        orderRepository.deleteById(id);
    }

    @Override
    public List<Order> getOrderByEmailAndStatus(String email, String status) {
        //finds all the orders that matches the status and email passed
        return orderRepository.findByEmailAndStatus(email,status);
    }

}
