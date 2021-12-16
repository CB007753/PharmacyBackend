package com.cb007753.pharmacybackend.AndroidConnection;

import com.cb007753.pharmacybackend.Model.RegistrationDTO;
import com.cb007753.pharmacybackend.Service.UserService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mobile/")
public class API {

    @Autowired
    private UserService userService;





    @PostMapping("User/Register")
    public JSONObject user_register(@RequestBody RegistrationDTO registrationDTO){

        JSONObject jsonObject = new JSONObject();
        userService.save(registrationDTO);

        jsonObject.put("Response","User Registered Successfully");
        return jsonObject;
    }

}
