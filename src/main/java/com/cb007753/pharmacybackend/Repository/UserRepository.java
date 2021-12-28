package com.cb007753.pharmacybackend.Repository;


import com.cb007753.pharmacybackend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

        List<User> findDetailsByEmail(String email);

        User findByEmail(String email);
}
