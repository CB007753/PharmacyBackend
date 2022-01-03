package com.cb007753.pharmacybackend;

import com.cb007753.pharmacybackend.Model.Contact;
import com.cb007753.pharmacybackend.Model.Drugs;
import com.cb007753.pharmacybackend.Model.Order;
import com.cb007753.pharmacybackend.Model.User;
import com.cb007753.pharmacybackend.Repository.ContactRepository;
import com.cb007753.pharmacybackend.Repository.DrugRepository;
import com.cb007753.pharmacybackend.Repository.OrderRepository;
import com.cb007753.pharmacybackend.Repository.UserRepository;
import com.cb007753.pharmacybackend.Service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class PharmacyBackendApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DrugRepository drugRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    ContactRepository contactRepository;

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

        List<Drugs> drugList= drugRepository.findAll();

    }

    //Testing: get all On the way orders
    //Test Result: Test Success, Displays all orders with status as "On The Way"
    @Test
    @DisplayName("On the way orders")
    public void TestGetOrderByStatus()
    {

        String status="On The Way";
        List<Order> orderList = orderRepository.findByStatus(status);

        //status of the founded order should match "On The Way"
        Assertions.assertEquals(status, orderList.get(0).getStatus());

    }

    //Get On the way Order details By Order Status and email
    //Test Result: Success, displays the "on the way orders" of the selected pharmacist(email).
    @Test
    @DisplayName("On The Way Order Details By Status and Email")
    public void GetOTWOrderDetailsByStatusAndEmail()
    {

        String status="On The Way";
        String email="shifny@gmail.com";


        List<Order> orderList = orderRepository.findByEmailAndStatus(email,status);

        //status of the founded order should match "On The Way"
        Assertions.assertEquals(status, orderList.get(0).getStatus());
        //email of the founded order should match "shifny@gmail.com"
        Assertions.assertEquals(email, orderList.get(0).getEmail());

    }

    //Get Delivered Order details By Order Status and email
    //Test Result: Success, displays the "Delivered" orders of the selected pharmacist(email).
    @Test
    @DisplayName("Delivered Order Details By Status and Email")
    public void GetDeliveredOrderDetailsByStatusAndEmail()
    {

        String status="Delivered";
        String email="shifny@gmail.com";


        List<Order> orderList = orderRepository.findByEmailAndStatus(email,status);

        //status of the founded order should match "On The Way"
        Assertions.assertEquals(status, orderList.get(0).getStatus());
        //email of the founded order should match "shifny@gmail.com"
        Assertions.assertEquals(email, orderList.get(0).getEmail());

    }

    //Delete the row from order table which matches the id provided
    //Test Result: Success, deletes the row which contains the provided id
    @Test
    @DisplayName("Delete Order")
    public void DeleteOrderByID()
    {
        int id = 6;

        orderService.deleteDrug((long) id);

    }


    //Testing: get all delivered orders
    //Test Result: Test Success, Displays all orders with status as "Delivered"
    @Test
    @DisplayName("Delivered orders")
    public void TestGetDeliveredOrder()
    {

        String status="Delivered";
        List<Order> orderList = orderRepository.findByStatus(status);

        //status of the founded order should match "On The Way"
        Assertions.assertEquals(status, orderList.get(0).getStatus());

    }

    //Testing: get all users
    //Test Result: Test Success, Displays all users with details
    @Test
    @DisplayName("All Users")
    public void TestGetAllUser()
    {

        List<User> userList = userRepository.findAll();

    }

    //Testing: get all messages
    //Test Result: Test Success, Displays all messages
    @Test
    @DisplayName("All Message")
    public void TestGetAllMessage()
    {

        List<Contact> contactList = contactRepository.findAll();

    }

}
