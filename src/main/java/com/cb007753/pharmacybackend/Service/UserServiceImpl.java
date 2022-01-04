package com.cb007753.pharmacybackend.Service;

import com.cb007753.pharmacybackend.Model.RegistrationDTO;
import com.cb007753.pharmacybackend.Model.Role;
import com.cb007753.pharmacybackend.Model.User;
import com.cb007753.pharmacybackend.Repository.UserRepository;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



//--------------------------------------------------------------------------------------------------------

    //Constructors

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    public UserServiceImpl() {
    }

//--------------------------------------------------------------------------------------------------------

    //Overrided functions

    @Override
    public User getUserByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    @Override
    public User save(RegistrationDTO registrationDTO) {

    User user =new User(
            registrationDTO.getFullname(),
            registrationDTO.getEmail(),
            bCryptPasswordEncoder.encode(registrationDTO.getPassword()),
            registrationDTO.getMobile(),
            Arrays.asList(new Role("ROLE_USER"))
        );

        userRepository.save(user);

        return userRepository.findByEmail(user.getEmail());
    }

    //Admin- add supplier function
    @Override
    public User saveSupplier(RegistrationDTO registrationDTO) {

        User user =new User(
                registrationDTO.getFullname(),
                registrationDTO.getEmail(),
                bCryptPasswordEncoder.encode(registrationDTO.getPassword()),
                registrationDTO.getMobile(),
                Arrays.asList(new Role("ROLE_SUPPLIER"))
        );

        userRepository.save(user);

        return userRepository.findByEmail(user.getEmail());

    }

    //this is used for edit profile function
    @Override
    public User saveUser(User user) {

        User user1 =new User(
                user.getId(),
                user.getFullname(),
                user.getEmail(),
                user.getPassword(),
                user.getMobile()
            );

        userRepository.save(user1);

        return userRepository.findByEmail(user.getEmail());

    }

    @Override
    public void deleteFile(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean passwordCheck(String pas_1, String pas_2) {
        return bCryptPasswordEncoder.matches(pas_1, pas_2);
    }

    @Override
    public boolean CheckIfUserExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);

            if(user==null){
                throw new UsernameNotFoundException("Invalid Username or Password");
            }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles())) ;
    }


    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){

        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

    }

}
