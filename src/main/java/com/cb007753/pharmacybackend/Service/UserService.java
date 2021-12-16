package com.cb007753.pharmacybackend.Service;


import com.cb007753.pharmacybackend.Model.RegistrationDTO;
import com.cb007753.pharmacybackend.Model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

//user detail service - used as DAO for user authentication
public interface UserService extends UserDetailsService {

    User getUserByEmail(String email);

    User save(RegistrationDTO registrationDTO);

    boolean passwordCheck(String pas_1, String pas_2);
    boolean CheckIfUserExists(String email);

}
