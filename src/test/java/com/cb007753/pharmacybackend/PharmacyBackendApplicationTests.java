package com.cb007753.pharmacybackend;

import com.cb007753.pharmacybackend.Model.User;
import com.cb007753.pharmacybackend.Repo.UserRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class PharmacyBackendApplicationTests {

    @Autowired
    UserRepo userRepo;

    //Testing: get user details by providing email as input
    @Test
    @DisplayName("User Details")
    public void GetUserDetails(){

        String email="neji@gmail.com";

        List<User> userdetail = userRepo.findUserDetails(email);
    }

}
