package com.cb007753.pharmacybackend.Controller;

import com.cb007753.pharmacybackend.Model.RegistrationDTO;
import com.cb007753.pharmacybackend.Repository.UserRepository;
import com.cb007753.pharmacybackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/adduser")
public class AdminAddUserController {
    //This page is an add user page which will be done by admin(add pharmacist)
    private final UserService userService;

    @Autowired
    private UserRepository userRepository;

    public AdminAddUserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public RegistrationDTO registrationDTO() {

        return new RegistrationDTO();

    }

    @GetMapping
    public String showAddUserForm(Model model) {

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails=(UserDetails)authentication.getPrincipal();
        model.addAttribute("useremail",userDetails);

        return "AddUserAdmin";
    }

    @PostMapping
    public String AddUserAccount(@ModelAttribute("user") @Valid RegistrationDTO registrationDto, BindingResult bindingResult)
    {
        //this try catch mostly catches the error caused by mobile number length
        try{
            if (CheckIfUserExists(registrationDto.getEmail()))
            {
                return "redirect:/admin/adduser?unsuccess";
            }
            else {
                userService.save(registrationDto);
                return "redirect:/admin/adduser?success";
            }
        }
        catch (Exception e){
            return "redirect:/admin/adduser?error";
        }

    }


    public boolean CheckIfUserExists(String email) {

        //returns true if there is user in the database with the same email
        return userRepository.findByEmail(email) != null;

    }
}
