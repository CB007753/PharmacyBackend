package com.cb007753.pharmacybackend.Repository;

import com.cb007753.pharmacybackend.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

}
