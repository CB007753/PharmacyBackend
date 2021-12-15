package com.cb007753.pharmacybackend.Service;


import com.cb007753.pharmacybackend.Model.RegistrationDTO;
import com.cb007753.pharmacybackend.Model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServiceInterface extends UserDetailsService {

    User getUser(String email);

    User saveUser(RegistrationDTO registrationDTO);

    boolean checkPassword(String pas_1, String pas_2);
    boolean checkUserExist(String email);

}
