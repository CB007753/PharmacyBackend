package com.cb007753.pharmacybackend.Service;

import com.cb007753.pharmacybackend.Model.RegistrationDTO;
import com.cb007753.pharmacybackend.Model.Role;
import com.cb007753.pharmacybackend.Model.User;
import com.cb007753.pharmacybackend.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;


@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepo userRepo;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


//--------------------------------------------------------------------------------------------------------

    //Constructors

    public UserService(UserRepo userRepo) {
        super();
        this.userRepo = userRepo;
    }

    public UserService() {
    }

//--------------------------------------------------------------------------------------------------------

    //Overrided functions

    @Override
    public User getUser(String email) {

        return userRepo.findUser(email);
    }

    @Override
    public User saveUser(RegistrationDTO registrationDTO) {

    User user =new User(
            registrationDTO.getFullname(),
            registrationDTO.getEmail(),
            bCryptPasswordEncoder.encode(registrationDTO.getPassword()),
            registrationDTO.getMobile(),
            Arrays.asList(new Role("ROLE_USER"))
        );

        userRepo.save(user);

        return userRepo.findUser(user.getEmail());
    }

    @Override
    public boolean checkPassword(String pas_1, String pas_2) {
        return bCryptPasswordEncoder.matches(pas_1, pas_2);
    }

    @Override
    public boolean checkUserExist(String email) {
        return userRepo.findUser(email) !=null ? true : false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user =userRepo.findUser(username);

            if(user==null){
                throw new UsernameNotFoundException("Invalid Username or Password");
            }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles())) ;
    }


    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){

        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

    }

}
