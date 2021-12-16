package com.cb007753.pharmacybackend;

import com.cb007753.pharmacybackend.Model.User;
import com.cb007753.pharmacybackend.Repository.UserRepository;
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
    UserRepository userRepository;

    //Testing: find user details by providing email as input
    //Test Result: Test Success, println was not printing the user details, need to find a solution for that
    @Test
    @DisplayName("User Details")
    public void FindUserDetailsByEmail(){

        String email="neji@gmail.com";

        List<User> userDetailsList = userRepository.findDetailsByEmail(email) ;
        System.out.println(userDetailsList);

    }

}
