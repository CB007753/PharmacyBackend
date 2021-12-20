package com.cb007753.pharmacybackend.AndroidConnection;

import com.cb007753.pharmacybackend.Model.*;
import com.cb007753.pharmacybackend.Repository.BuyDrugRepository;
import com.cb007753.pharmacybackend.Repository.DrugRepository;
import com.cb007753.pharmacybackend.Repository.UserRepository;
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
}
