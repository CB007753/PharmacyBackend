package com.cb007753.pharmacybackend.Service;


import com.cb007753.pharmacybackend.Model.RegistrationDTO;
import com.cb007753.pharmacybackend.Model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

//user detail service - used as DAO for user authentication
public interface UserService extends UserDetailsService {

    //to get user linked with the email provided
    User getUserByEmail(String email);

    //to save user to database- register pharmacist
    User save(RegistrationDTO registrationDTO);

    //to save user to database- register supplier
    User saveSupplier(RegistrationDTO registrationDTO);

    //to edit profile details
    User saveUser(User user);

    //Admin- delete user by id
    void deleteFile(Long id);

    //to check whether the user entered pass is correct
    boolean passwordCheck(String pas_1, String pas_2);

    //to check whether the user linked with the email provided exists, returns true/false
    boolean CheckIfUserExists(String email);

}
