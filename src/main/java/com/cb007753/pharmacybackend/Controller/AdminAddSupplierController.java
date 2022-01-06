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
@RequestMapping("/admin/addsupplier")
public class AdminAddSupplierController {

    //This page is an add supplier page which will be done by admin(add supplier)
    private final UserService userService;

    @Autowired
    private UserRepository userRepository;

    //constructors
    public AdminAddSupplierController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public RegistrationDTO registrationDTO() {

        return new RegistrationDTO();

    }

    @GetMapping
    public String showAddSupplierForm(Model model) {

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails=(UserDetails)authentication.getPrincipal();
        model.addAttribute("useremail",userDetails);

        return "AddSupplierAdmin";
    }

    @PostMapping
    public String AddSupplierAccount(@ModelAttribute("user") @Valid RegistrationDTO registrationDto, BindingResult bindingResult)
    {
        //this try catch mostly catches the error caused by mobile number length
        try{
            if (CheckIfUserExists(registrationDto.getEmail()))
            {
                return "redirect:/admin/addsupplier?unsuccess";
            }
            else {
                userService.saveSupplier(registrationDto);
                return "redirect:/admin/addsupplier?success";
            }
        }
        catch (Exception e){
            return "redirect:/admin/addsupplier?error";
        }

    }


    public boolean CheckIfUserExists(String email) {

        //returns true if there is user in the database with the same email
        return userRepository.findByEmail(email) != null;

    }
}
