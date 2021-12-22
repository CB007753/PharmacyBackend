package com.cb007753.pharmacybackend.Repository;

import com.cb007753.pharmacybackend.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    //this will only find and display the orders(on the way, order history) ordered by currently active pharmacist.
    List<Order> findByEmailAndStatus(String email, String status);

    //this will only find and display the orders with delivered status (displays in home).
    List<Order> findByStatus(String status);

}
