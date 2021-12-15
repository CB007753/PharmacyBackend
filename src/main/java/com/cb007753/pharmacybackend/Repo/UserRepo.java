package com.cb007753.pharmacybackend.Repo;


import com.cb007753.pharmacybackend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

        List<User> findUserDetails(String email);
        User findUser(String email);
}
