package com.cb007753.pharmacybackend.Controller;

import com.cb007753.pharmacybackend.Model.Order;
import com.cb007753.pharmacybackend.Model.User;
import com.cb007753.pharmacybackend.Repository.OrderRepository;
import com.cb007753.pharmacybackend.Repository.UserRepository;
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

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

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

    //displays all the delivered orders
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

    //this deletes the selected drug from buy_drug table in database
    @GetMapping(value = "admin/deleteuser/{id}")
    public String deleteUser(@PathVariable("id") Long id)
    {
        userService.deleteFile(id);
        return  "redirect:/admin/allusers?deleteusersuccess";
    }
}
