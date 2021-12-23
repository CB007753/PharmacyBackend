package com.cb007753.pharmacybackend.AndroidConnection;

import com.cb007753.pharmacybackend.Model.*;
import com.cb007753.pharmacybackend.Repository.BuyDrugRepository;
import com.cb007753.pharmacybackend.Repository.DrugRepository;
import com.cb007753.pharmacybackend.Repository.OrderRepository;
import com.cb007753.pharmacybackend.Repository.UserRepository;
import com.cb007753.pharmacybackend.Service.ContactService;
import com.cb007753.pharmacybackend.Service.OrderService;
import com.cb007753.pharmacybackend.Service.UserService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/mobile/")
public class API {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository  userRepository;

    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private BuyDrugRepository buyDrugRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ContactService contactService;


//-----------------------------------------------------------------------------------------------

    //Register
    @PostMapping("User/Register")
    public JSONObject user_register(@RequestBody RegistrationDTO registrationDTO){

        JSONObject jsonObject = new JSONObject();
        userService.save(registrationDTO);

        jsonObject.put("Response","User Registered Successfully");
        return jsonObject;
    }

//-----------------------------------------------------------------------------------------------

    //Login
    @GetMapping("login/{username}/{password}")
    public JSONObject loginResponse(@PathVariable(value = "username")String username,
                                    @PathVariable(value = "password")String password){

        JSONObject jsonObject = new JSONObject();
        User user = userRepository.findByEmail(username);

        if(user != null){
            if(userService.passwordCheck(password, user.getPassword())){
                jsonObject.put("Response", "Correct Password");
            }else {
                jsonObject.put("Response", "Password Incorrect");
            }
        }
        else {
            jsonObject.put("Response", "User does not exist");
        }
        return jsonObject;
    }

//-----------------------------------------------------------------------------------------------

    //Display all drugs in pharmacy
    //this function was temporily used earlier to test drugs display in home page- test result: success
    @GetMapping ( "User/druglist")
    public List<Drugs> getAllDrugs()
    {

        return drugRepository.findAll();

    }

//-----------------------------------------------------------------------------------------------

    //Display all drugs in supplier market
    @GetMapping ( "User/buydruglist")
    public List<BuyDrugs> getBuyDrugs()
    {

        return buyDrugRepository.findAll();

    }

//-----------------------------------------------------------------------------------------------

    //placing the order(order being saved at order table)
    @PostMapping("User/placeorder")
    public JSONObject user_place_order(@RequestBody Order order) throws IOException {

        JSONObject jsonObject=new JSONObject();

        orderService.saveOrder(order);

        jsonObject.put("Response","Order Added");
        return jsonObject;

    }

//-----------------------------------------------------------------------------------------------

    //this will only find and display the orders(on the way) ordered by currently active pharmacist.
    @GetMapping("User/onthewayorders/{email}/{status}")
    public List<Order> getOnTheWayPharmacistOrders(@PathVariable(value = "email")String username,@PathVariable(value = "status")String status)
    {
        return orderRepository.findByEmailAndStatus(username,status);
    }

//-----------------------------------------------------------------------------------------------

    //this will only find and display the orders(order history) ordered by currently active pharmacist.
    @GetMapping("User/deliveredorders/{email}/{status}")
    public List<Order> getDeliveredPharmacistOrders(@PathVariable(value = "email")String username,@PathVariable(value = "status")String status)
    {
        return orderRepository.findByEmailAndStatus(username,status);
    }

//-----------------------------------------------------------------------------------------------

    //this update the order detail's status column to "Delivered".
    @PostMapping("User/updateorder")
    public JSONObject update_Order(@RequestBody Order order) throws IOException {

        //save function does the update also
        JSONObject jsonObject=new JSONObject();

        orderService.saveOrder(order);

        jsonObject.put("Response","Updated");
        return jsonObject;

    }

    //-----------------------------------------------------------------------------------------------

    //this will find and get all the orders with status "Delivered".
    @GetMapping("User/ordersinstock/{status}")
    public List<Order> getDeliveredOrders(@PathVariable(value = "status")String status)
    {
        return orderRepository.findByStatus(status);
    }

    //-----------------------------------------------------------------------------------------------

    //this will find and get all details of user linked with provided email.
    @GetMapping("User/finduser/{email}")
    public User getUserByEmail(@PathVariable(value = "email")String username)
    {
        return userRepository.findByEmail(username);
    }

    //-----------------------------------------------------------------------------------------------

    //Deleting the order(order which matches the values retrieved)
    //This does not work- below method is used instead
    @DeleteMapping("User/deleteorder")
    public JSONObject user_delete_order(@RequestBody Order order) throws IOException {

        JSONObject jsonObject=new JSONObject();

        orderService.deleteOrder(order);

        jsonObject.put("Response","Order Deleted");
        return jsonObject;

    }
//-----------------------------------------------------------------------------------------------

    //Deleting the order(order which matches the id)
    @GetMapping(value = "User/deletedrug/{id}")
    public String delete_drug(@PathVariable("id") Long id)
    {
        orderService.deleteDrug(id);

        return  "success";
    }

//-----------------------------------------------------------------------------------------------

    //This is used for pharmacist to send message to admin from front end(add the message to database contact table)
    @PostMapping("User/addmessage")
    public JSONObject add_message(@RequestBody Contact contact) throws IOException {
        JSONObject jsonObject=new JSONObject();

        contactService.AddMessage(contact);

        jsonObject.put("Response","Message Sent");
        return jsonObject;
    }

}
