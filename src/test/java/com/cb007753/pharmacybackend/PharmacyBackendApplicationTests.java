package com.cb007753.pharmacybackend;

import com.cb007753.pharmacybackend.Model.Drugs;
import com.cb007753.pharmacybackend.Model.User;
import com.cb007753.pharmacybackend.Repository.DrugRepository;
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

    @Autowired
    DrugRepository drugRepository;

    //Testing: find user details by providing email as input
    //Test Result: Test Success, found the details associated with the email, found the role of the user too.
    @Test
    @DisplayName("User Details")
    public void FindUserDetailsByEmail(){

        String email="shifny@gmail.com";

        List<User> userDetailsList = userRepository.findDetailsByEmail(email) ;
        System.out.println(userDetailsList);

    }

    //Testing: get all the Drugs
    //Test Result: Test Success, Displays all drugs available in the database(drugs table)
    @Test
    @DisplayName("All Drugs")
    public void TestGetAllDrugs()
    {

        List<Drugs> foodList= drugRepository.findAll();

    }

}
