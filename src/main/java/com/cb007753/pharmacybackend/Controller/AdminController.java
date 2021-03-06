package com.cb007753.pharmacybackend.Controller;

import com.cb007753.pharmacybackend.Model.Contact;
import com.cb007753.pharmacybackend.Model.Order;
import com.cb007753.pharmacybackend.Model.User;
import com.cb007753.pharmacybackend.Repository.ContactRepository;
import com.cb007753.pharmacybackend.Repository.OrderRepository;
import com.cb007753.pharmacybackend.Repository.UserRepository;
import com.cb007753.pharmacybackend.Service.ContactService;
import com.cb007753.pharmacybackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    ContactService contactService;

    //    -------------------------------------------------------------------------------------------------

    //displays all the on the way orders
    @RequestMapping(value = "/admin/allotworders/{status}")
    public String viewAllOTWOrders(@PathVariable("status") String status, Model model)
    {
        List<Order> adminOTWList = orderRepository.findByStatus(status);

        model.addAttribute("adminOTWList",adminOTWList);

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails=(UserDetails)authentication.getPrincipal();
        model.addAttribute("useremail",userDetails);

        //redirecting to ViewAllOTWAdmin html page
        return "ViewAllOTWAdmin";
    }

    //    -------------------------------------------------------------------------------------------------

    //displays all the delivered orders
    @RequestMapping(value = "/admin/alldeliveredorders/{status}")
    public String viewAllDeliveredOrders(@PathVariable("status") String status, Model model)
    {
        List<Order> adminDeliveredList = orderRepository.findByStatus(status);

        model.addAttribute("adminDeliveredList",adminDeliveredList);

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails=(UserDetails)authentication.getPrincipal();
        model.addAttribute("useremail",userDetails);

        //redirecting to ViewAllDeliveredAdmin html page
        return "ViewAllDeliveredAdmin";
    }

//    -------------------------------------------------------------------------------------------------

    //displays all the users
    @RequestMapping(value = "/admin/allusers")
    public String viewAllUsers(Model model)
    {
        List<User> adminUserList = userRepository.findAll();

        model.addAttribute("adminUserList",adminUserList);

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails=(UserDetails)authentication.getPrincipal();
        model.addAttribute("useremail",userDetails);

        //redirecting to ViewAllUsersAdmin html page
        return "ViewAllUsersAdmin";
    }

    //    -------------------------------------------------------------------------------------------------

    //This is a confirmation page to delete the selected user
    @GetMapping(value = "/admin/deletepage/{id}")
    public String DeleteButton(@PathVariable("id") Long id, Model model)
    {
        Optional<User> delete_profile = userRepository.findById(id);

        model.addAttribute("id",delete_profile.get().getId());
        model.addAttribute("name",delete_profile.get().getFullname());
        model.addAttribute("email",delete_profile.get().getEmail());
        model.addAttribute("contact",delete_profile.get().getMobile());
        model.addAttribute("password",delete_profile.get().getPassword());

        //redirecting to EditProfile html page
        return "DeleteUser";
    }

    //    -------------------------------------------------------------------------------------------------

    //this deletes the selected user from database
    @GetMapping(value = "/admin/deleteuser/{id}")
    public String deleteUser(@RequestParam("user_id") Long id)
    {
        userService.deleteFile(id);
        return  "redirect:/admin/allusers?deleteusersuccess";
    }

    //    -------------------------------------------------------------------------------------------------

    //displays all the contact messages-admin
    @RequestMapping(value = "/admin/allmessages")
    public String viewAllMessage(Model model)
    {
        List<Contact> adminMessageList = contactRepository.findAll();

        model.addAttribute("adminMessageList",adminMessageList);

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails=(UserDetails)authentication.getPrincipal();
        model.addAttribute("useremail",userDetails);

        //redirecting to ViewAllUsersAdmin html page
        return "ViewAllMessageAdmin";
    }

    //    -------------------------------------------------------------------------------------------------

    //this deletes the selected message
    @GetMapping(value = "/admin/deletemessage/{id}")
    public String deleteMessage(@PathVariable("id") Long id)
    {
        contactService.deleteFile(id);
        return  "redirect:/admin/allmessages?deletemsgsuccess";
    }
}
